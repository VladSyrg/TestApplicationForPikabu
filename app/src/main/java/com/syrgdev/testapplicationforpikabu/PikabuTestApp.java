package com.syrgdev.testapplicationforpikabu;

import android.app.Application;

import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application.ApplicationComponent;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application.ApplicationModule;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application.DaggerApplicationComponent;

public class PikabuTestApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}