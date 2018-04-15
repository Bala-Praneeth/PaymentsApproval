package com.example.vitaly.paymentsapproval.model.api;

import com.example.vitaly.paymentsapproval.model.data.Payment;

import java.util.ArrayList;

import io.reactivex.Observable;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by vitaliy on 02/04/2018.
 */

public interface ApiInterface {
    @GET("Applications/1")
    Single<ArrayList<Payment>> getPayments();
}
