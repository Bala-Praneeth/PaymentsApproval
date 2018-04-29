package com.example.vitaly.paymentsapproval.other;

import android.app.Application;
import android.content.Context;

import com.example.vitaly.paymentsapproval.model.IModel;
import com.example.vitaly.paymentsapproval.model.ModelImpl;
import com.example.vitaly.paymentsapproval.model.test.Fake;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    private final Application mApplication;

    public ModelModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    IModel provideIModel() {return new ModelImpl(mApplication);}

    @Provides
    @Singleton
    Fake provideFake() {return Fake.getInstance(mApplication);}

    @Provides
    Context provideContext() {return mApplication.getApplicationContext();}
}
