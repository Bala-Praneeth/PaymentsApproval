package com.example.vitaly.paymentsapproval.view;

import com.example.vitaly.paymentsapproval.model.data.Payment;

public interface ActivityCallback {
    void startPaymentInfoFragment(Payment payment);
}
