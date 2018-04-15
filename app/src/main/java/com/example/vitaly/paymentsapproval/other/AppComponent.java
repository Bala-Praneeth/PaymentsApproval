package com.example.vitaly.paymentsapproval.other;

import com.example.vitaly.paymentsapproval.presenter.PaymentsListPresenter;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentsListFragment;

import dagger.Component;

@Component(modules = {ModelModule.class})
public interface AppComponent {

    void inject(PaymentsListFragment paymentsListFragment);

}
