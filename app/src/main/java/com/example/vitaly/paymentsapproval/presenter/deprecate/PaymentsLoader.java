package com.example.vitaly.paymentsapproval.presenter.deprecate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vitaly.paymentsapproval.model.api.NetworkUtils;
import com.example.vitaly.paymentsapproval.model.data.Payment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by vitaliy on 05/03/2018.
 */

public class PaymentsLoader extends AsyncTaskLoader<ArrayList<Payment>> {
    ArrayList<Payment> payments;

    public PaymentsLoader(Context context) {
        super(context);
        this.payments = new ArrayList<>();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Payment> loadInBackground() {

        String jsonString = NetworkUtils.getPaymentInfo();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int i = 0;
            while (i < jsonArray.length()) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Payment.Builder paymentBuilder = new Payment.Builder(jsonObject.getString("GUID"));
                paymentBuilder.setNumber(jsonObject.getString("Номер"));

                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                paymentBuilder.setDate(sdf.parse(jsonObject.getString("Дата")));

                payments.add(paymentBuilder.build());
                i++;
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        return payments;
    }
}
