package com.example.vitaly.paymentsapproval.model.data.source.local;

import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.model.data.PaymentsDataSource;
import com.example.vitaly.paymentsapproval.other.AppExecutors;
import com.example.vitaly.paymentsapproval.other.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class PaymentsLocalDataSource implements PaymentsDataSource {

    private static volatile PaymentsLocalDataSource INSTANCE;

    private PaymentsDao mPaymentsDao;

    private AppExecutors mAppExecutors;

    private SchedulerProvider mSchedulerProvider;

    private PaymentsLocalDataSource(AppExecutors appExecutors, PaymentsDao paymentsDao, SchedulerProvider schedulerProvider) {
        mAppExecutors = appExecutors;
        mPaymentsDao = paymentsDao;
        mSchedulerProvider = schedulerProvider;
    }

    public static PaymentsLocalDataSource getInstance(AppExecutors appExecutors, PaymentsDao paymentsDao, SchedulerProvider schedulerProvider) {
        synchronized (PaymentsLocalDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new PaymentsLocalDataSource(appExecutors, paymentsDao, schedulerProvider);
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPayments(LoadPaymentsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Payment> payments = mPaymentsDao.getPayments();
                mAppExecutors.mainThread().execute(() -> {
                    if (payments.isEmpty()) {
                        callback.onDataNotAvailable();
                    } else {
                        callback.onPaymentsLoad(payments);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public Flowable<List<Payment>> getPaymentsRX() {
        return mPaymentsDao.getPaymentsRX()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui());
    }

    @Override
    public void cachePayments(ArrayList<Payment> payments) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (Payment payment: payments) {
                    mPaymentsDao.insertPayment(payment);
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
