package com.example.vitaly.paymentsapproval.presenter;

import com.example.vitaly.paymentsapproval.model.IModel;
import com.example.vitaly.paymentsapproval.model.ModelImpl;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.model.test.Fake;
import com.example.vitaly.paymentsapproval.other.App;
import com.example.vitaly.paymentsapproval.other.Const;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentsListView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by vitaliy on 31/03/2018.
 */

public class PaymentsListPresenter implements IPresenter {

    @Inject
    IModel model;

    private PaymentsListView view;

    public PaymentsListPresenter(PaymentsListView view) {
        this.view = view;
    }

    public void loadPayments() {
        if (Const.USE_FAKE_PAYMENTS) {
            view.showPaymentsList(Fake.getFakePaymentsFromJsonFile(view.getCtx()));
        } else {
            model.getPaymentsList().subscribe(new SingleObserver<ArrayList<Payment>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    String test = "test";
                }

                @Override
                public void onSuccess(ArrayList<Payment> payments) {
                    view.showPaymentsList(payments);
                }

                @Override
                public void onError(Throwable e) {
                    view.showError(e.getMessage());
                }
            });
        }
    }

    public void clickPayment(Payment payment) {
        view.startPaymentInfoFragment(payment);
    }

    @Override
    public void onStop() {

    }
}
