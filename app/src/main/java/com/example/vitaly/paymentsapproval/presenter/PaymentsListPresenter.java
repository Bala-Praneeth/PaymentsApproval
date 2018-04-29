package com.example.vitaly.paymentsapproval.presenter;

import android.content.Context;

import com.example.vitaly.paymentsapproval.model.IModel;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.model.data.PaymentsDataSource;
import com.example.vitaly.paymentsapproval.model.data.PaymentsRepository;
import com.example.vitaly.paymentsapproval.model.data.source.local.PaymentsDao;
import com.example.vitaly.paymentsapproval.model.data.source.local.PaymentsDatabase;
import com.example.vitaly.paymentsapproval.model.data.source.local.PaymentsLocalDataSource;
import com.example.vitaly.paymentsapproval.model.test.Fake;
import com.example.vitaly.paymentsapproval.other.App;
import com.example.vitaly.paymentsapproval.other.AppExecutors;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentsListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by vitaliy on 31/03/2018.
 */

public class PaymentsListPresenter implements IPresenter {

    @Inject
    protected IModel model;

    @Inject
    protected Fake fake;

    @Inject
    protected Context appContext;

    private PaymentsListView view;

    private PaymentsRepository paymentsRepository;

    public PaymentsListPresenter(PaymentsListView view) {
        App.getComponent().inject(this);
        this.view = view;

        paymentsRepository = PaymentsRepository.getInstance(
                PaymentsLocalDataSource.getInstance(new AppExecutors(),
                        PaymentsDatabase.getInstance(appContext).paymentsDao()));
    }

    public void loadPayments() {

        if (model.isDemoMode()) {
            /*ArrayList<Payment> payments = fake.getFakePaymentsFromJsonFile();
            paymentsRepository.cachePayments(payments);
            view.showPaymentsList(payments);*/
            paymentsRepository.getPayments(new PaymentsDataSource.LoadPaymentsCallback() {
                @Override
                public void onPaymentsLoad(List<Payment> payments) {
                    view.showPaymentsList(new ArrayList<>(payments));
                }

                @Override
                public void onDataNotAvailable() {

                }
            });

        } else {
            view.showLoading();
            model.getPaymentsList().subscribe(new SingleObserver<ArrayList<Payment>>() {
                @Override
                public void onSubscribe(Disposable d) {}

                @Override
                public void onSuccess(ArrayList<Payment> payments) {
                    view.hideLoading();
                    view.showPaymentsList(payments);
                }

                @Override
                public void onError(Throwable e) {
                    view.hideLoading();
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
