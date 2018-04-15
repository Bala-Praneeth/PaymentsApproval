package com.example.vitaly.paymentsapproval.model;

import com.example.vitaly.paymentsapproval.model.data.Payment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by vitaliy on 02/04/2018.
 */

public interface IModel {
    Single<ArrayList<Payment>> getPaymentsList();
}
