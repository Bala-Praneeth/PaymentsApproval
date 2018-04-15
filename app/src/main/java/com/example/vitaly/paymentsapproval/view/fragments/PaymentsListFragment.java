package com.example.vitaly.paymentsapproval.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitaly.paymentsapproval.R;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.other.App;
import com.example.vitaly.paymentsapproval.presenter.PaymentsListPresenter;
import com.example.vitaly.paymentsapproval.view.ActivityCallback;
import com.example.vitaly.paymentsapproval.view.adapters.PaymentsListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentsListFragment extends BaseFragment implements PaymentsListView {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private PaymentsListPresenter presenter = new PaymentsListPresenter(this);

    private PaymentsListAdapter adapter;

    private ActivityCallback activityCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragments_payments_list, container, false);

        ButterKnife.bind(this, view);

        adapter = new PaymentsListAdapter(new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.loadPayments();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            activityCallback = (ActivityCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement activityCallback");
        }

    }

    @Override
    public void showPaymentsList(ArrayList<Payment> list) {
        adapter.setPaymentArrayList(list);
    }

    @Override
    public void startPaymentInfoFragment(Payment payment) {
        activityCallback.startPaymentInfoFragment(payment);
    }

    @Override
    public void showError(String error) {
        showToast(error);
    }

    @Override
    public Context getCtx() {
        return super.getContext();
    }

    private void showToast(String error) {
        Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG).show();
    }

}
