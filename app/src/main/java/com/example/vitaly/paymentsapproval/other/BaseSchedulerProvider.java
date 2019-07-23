package com.example.vitaly.paymentsapproval.other;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler io();

    Scheduler ui();

}
