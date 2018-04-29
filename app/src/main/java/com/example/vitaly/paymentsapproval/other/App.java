package com.example.vitaly.paymentsapproval.other;

import android.app.Application;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .modelModule(new ModelModule(this))
                .build();
    }
}
