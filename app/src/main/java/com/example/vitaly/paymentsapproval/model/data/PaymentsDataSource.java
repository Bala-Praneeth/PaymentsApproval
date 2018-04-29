package com.example.vitaly.paymentsapproval.model.data;

import java.util.ArrayList;
import java.util.List;

public interface PaymentsDataSource {

    interface LoadPaymentsCallback {

        void onPaymentsLoad(List<Payment> payments);

        void onDataNotAvailable();

    }

    void getPayments(LoadPaymentsCallback callback);

    void cachePayments(ArrayList<Payment> payments);
}
