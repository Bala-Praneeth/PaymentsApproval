package com.example.vitaly.paymentsapproval.model.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class PaymentsRepository implements PaymentsDataSource {

    private static PaymentsRepository INSTANCE;

    private final PaymentsDataSource mPaymentsLocalDataSource;

    private PaymentsRepository(PaymentsDataSource paymentsLocalDataSource) {
        mPaymentsLocalDataSource = paymentsLocalDataSource;
    }

    public static PaymentsRepository getInstance(@NonNull PaymentsDataSource paymentsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new PaymentsRepository(paymentsLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getPayments(LoadPaymentsCallback callback) {
        mPaymentsLocalDataSource.getPayments(new LoadPaymentsCallback() {
            @Override
            public void onPaymentsLoad(List<Payment> payments) {
                callback.onPaymentsLoad(payments);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public Flowable<List<Payment>> getPaymentsRX() {
        return mPaymentsLocalDataSource.getPaymentsRX();
    }

    @Override
    public void cachePayments(ArrayList<Payment> payments) {
        mPaymentsLocalDataSource.cachePayments(payments);
    }
}
