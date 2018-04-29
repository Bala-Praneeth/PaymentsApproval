package com.example.vitaly.paymentsapproval.model.test;

import android.app.Application;
import android.content.Context;

import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class Fake {

    private static Fake INSTANCE;

    private Application mApplication;

    private Fake(Application mApplication) {
        this.mApplication = mApplication;
    }

    public static Fake getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new Fake(application);
        }
        return INSTANCE;
    }

    public ArrayList<Payment> getFakePayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        Payment payment;
        Calendar calendar = Calendar.getInstance();

        calendar.set(2018,1,2);
        payment = new Payment.Builder("3c7a0374-07e3-11e8-80e2-b083fee93cce")
                .setDate(calendar.getTime()).setNumber("00000000019").build();
        payments.add(payment);

        calendar.set(2018,2,5);
        payment = new Payment.Builder("3c7a0375-07e3-11e8-80e2-b083fee93cce")
                .setDate(calendar.getTime()).setNumber("00000000020").build();
        payments.add(payment);

        calendar.set(2018,3,8);
        payment = new Payment.Builder("3c7a0377-07e3-11e8-80e2-b083fee93cce")
                .setDate(calendar.getTime()).setNumber("00000000021").build();
        payments.add(payment);

        return payments;
    }

    public ArrayList<Payment> getFakePaymentsFromJsonFile() {
        ArrayList<Payment> payments;
        String JsonString;

        InputStream stream = mApplication.getResources().openRawResource(
                mApplication.getResources().getIdentifier("demo_payments",
                        "raw", mApplication.getPackageName()));

        try {
            int size = stream.available();
            byte[] buffer = new byte[size];
            int result = stream.read(buffer);
            JsonString = new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type itemsListType = new TypeToken<ArrayList<Payment>>() {}.getType();

        payments = gson.fromJson(JsonString, itemsListType);

        return payments;
    }
}
