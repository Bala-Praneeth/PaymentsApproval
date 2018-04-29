package com.example.vitaly.paymentsapproval.model.data.source.local;

import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.model.data.PaymentsDataSource;
import com.example.vitaly.paymentsapproval.other.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class PaymentsLocalDataSource implements PaymentsDataSource {

    private static volatile PaymentsLocalDataSource INSTANCE;

    private PaymentsDao mPaymentsDao;

    private AppExecutors mAppExecutors;

    private PaymentsLocalDataSource(AppExecutors appExecutors, PaymentsDao paymentsDao) {
        mAppExecutors = appExecutors;
        mPaymentsDao = paymentsDao;
    }

    public static PaymentsLocalDataSource getInstance(AppExecutors appExecutors, PaymentsDao paymentsDao) {
        synchronized (PaymentsLocalDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new PaymentsLocalDataSource(appExecutors, paymentsDao);
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
