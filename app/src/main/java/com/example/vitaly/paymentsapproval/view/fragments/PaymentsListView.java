package com.example.vitaly.paymentsapproval.view.fragments;

import android.content.Context;

import com.example.vitaly.paymentsapproval.model.data.Payment;

import java.util.ArrayList;

/**
 * Created by vitaliy on 31/03/2018.
 */

public interface PaymentsListView extends View {
    void showPaymentsList(ArrayList<Payment> list);
    void startPaymentInfoFragment(Payment payment);
    void showLoading();
    void hideLoading();
}
