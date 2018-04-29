package com.example.vitaly.paymentsapproval.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitaly.paymentsapproval.R;
import com.example.vitaly.paymentsapproval.model.data.Payment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentInfoFragment extends BaseFragment implements PaymentInfoView {
    @BindView(R.id.payment_info_layout) View paymentInfoLayout;
    @BindView(R.id.payment_number) TextView paymentNumber;
    @BindView(R.id.payment_date) TextView paymentDate;

    private static final String BUNDLE_PAYMENT_KEY = "BUNDLE_PAYMENT_KEY";

    public static PaymentInfoFragment newInstance(Payment payment) {
        PaymentInfoFragment myFragment = new PaymentInfoFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_PAYMENT_KEY, payment);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_info, container, false);

        ButterKnife.bind(this, view);

        showPaymentInfo(getPayment());

        return view;
    }

    private Payment getPayment () {
        return (Payment) getArguments().getSerializable(BUNDLE_PAYMENT_KEY);
    }

    @Override
    public void showPaymentInfo(Payment payment) {
        paymentNumber.setText(payment.getNumber());
        paymentDate.setText(payment.getDateAsString());
    }

    @Override
    public void showError(String error) {
        showToast(error);
    }

    private void showToast(String error) {
        Snackbar.make(paymentInfoLayout, error, Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_accept)
    void onAcceptClick(View v) {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.btn_dismiss)
    void onDismissClick(View v) {
        getActivity().onBackPressed();
    }
}
