package com.example.vitaly.paymentsapproval.other;

import com.example.vitaly.paymentsapproval.model.IModel;
import com.example.vitaly.paymentsapproval.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @Singleton
    IModel provideIModel() {return new ModelImpl();}

}
