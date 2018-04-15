package com.example.vitaly.paymentsapproval.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitaly.paymentsapproval.presenter.PaymentsListPresenter;
import com.example.vitaly.paymentsapproval.model.data.Payment;

import java.util.ArrayList;

/**
 * Created by vitaliy on 04/03/2018.
 */

public class PaymentsListAdapter extends RecyclerView.Adapter<PaymentsListAdapter.PaymentsViewHolder> {
    private ArrayList<Payment> paymentArrayList;
    private PaymentsListPresenter presenter;

    public PaymentsListAdapter(ArrayList<Payment> paymentArrayList, PaymentsListPresenter presenter) {
        this.paymentArrayList = paymentArrayList;
        this.presenter = presenter;
    }

    public void setPaymentArrayList(ArrayList<Payment> paymentArrayList) {
        this.paymentArrayList = paymentArrayList;
        notifyDataSetChanged();
    }

    class PaymentsViewHolder extends RecyclerView.ViewHolder {
        public final View itemView;

        public PaymentsViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @Override
    public PaymentsListAdapter.PaymentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payments_list_item, parent, false);
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PaymentsViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(PaymentsListAdapter.PaymentsViewHolder holder, int position) {
        Payment mCurrent = paymentArrayList.get(position);
        TextView text1 = holder.itemView.findViewById(android.R.id.text1);
        text1.setText(mCurrent.getNumber());
        TextView text2 = holder.itemView.findViewById(android.R.id.text2);
        text2.setText(mCurrent.getDateAsString());

        holder.itemView.setOnClickListener(view -> presenter.clickPayment(mCurrent));
    }

    @Override
    public int getItemCount() {
        return paymentArrayList.size();
    }
}
