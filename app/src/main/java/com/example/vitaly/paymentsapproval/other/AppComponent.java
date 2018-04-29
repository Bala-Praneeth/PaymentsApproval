package com.example.vitaly.paymentsapproval.other;

import com.example.vitaly.paymentsapproval.presenter.PaymentsListPresenter;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class})
public interface AppComponent {

    void inject(PaymentsListFragment paymentsListFragment);

    void inject(PaymentsListPresenter paymentsListPresenter);
}
