package com.example.vitaly.paymentsapproval;

import com.example.vitaly.paymentsapproval.model.IModel;
import com.example.vitaly.paymentsapproval.model.ModelImpl;
import com.example.vitaly.paymentsapproval.model.api.ApiInterface;
import com.example.vitaly.paymentsapproval.model.api.ApiModule;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.other.Const;
import com.example.vitaly.paymentsapproval.other.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by vitaliy on 03/04/2018.
 */

public class ApiModuleTest {
    //ApiInterfaceTest apiInterface;
    String credentials;
    IModel model;
    ApiInterface apiInterface;
    MockWebServer server;
    HttpUrl baseUrl;
    Utils utils = new Utils();

    @Before
    public void setUp() throws Exception {
        //model = new ModelImpl();
        server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(utils.readString("json/payments")));
        server.start();
        baseUrl = server.url("/");
    }

    /*@Test
    public void getPaymentsFromFile () {
        Utils utils = new Utils();
        Payment[] payments = utils.getGson().fromJson(utils.readString("json/payments"), Payment[].class);
        List<Payment> paymentArrayList = Arrays.asList(payments);
    }*/

    @Test
    public void getPayments() throws IOException {

        apiInterface = ApiModule.getApiInterface(baseUrl.toString());
        apiInterface.getPayments().subscribe(new SingleObserver<ArrayList<Payment>>() {
            @Override
            public void onSubscribe(Disposable d) {
                String test = "test";
            }

            @Override
            public void onSuccess(ArrayList<Payment> payments) {
                String test = "test";
            }

            @Override
            public void onError(Throwable e) {
                String test = "test";
                utils.log(e.getLocalizedMessage());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
