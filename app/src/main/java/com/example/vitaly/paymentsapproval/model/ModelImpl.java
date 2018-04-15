package com.example.vitaly.paymentsapproval.model;

import com.example.vitaly.paymentsapproval.model.api.ApiInterface;
import com.example.vitaly.paymentsapproval.model.api.ApiModule;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.other.Const;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vitaliy on 02/04/2018.
 */

public class ModelImpl implements IModel {

    ApiInterface apiInterface = ApiModule.getApiInterface(Const.BASE_URL);

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    @Override
    public Single<ArrayList<Payment>> getPaymentsList() {
        return apiInterface.getPayments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }
}
