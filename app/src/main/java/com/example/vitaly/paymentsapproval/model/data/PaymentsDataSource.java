package com.example.vitaly.paymentsapproval.model.data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public interface PaymentsDataSource {

    interface LoadPaymentsCallback {

        void onPaymentsLoad(List<Payment> payments);

        void onDataNotAvailable();

    }

    void getPayments(LoadPaymentsCallback callback);

    Flowable<List<Payment>> getPaymentsRX();

    void cachePayments(ArrayList<Payment> payments);
}
