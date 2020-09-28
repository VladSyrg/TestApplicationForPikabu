package com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application;

import android.app.Application;
import android.content.Context;

import com.syrgdev.testapplicationforpikabu.network.PikabuTestAPI;
import com.syrgdev.testapplicationforpikabu.common.ScreensManager;
import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManager;
import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Context getContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    ScreensManager getScreensManager() {
        return new ScreensManager();
    }

    @Singleton
    @Provides
    PersistenceManager getPersistenceManager(PikabuTestAPI pikabuTestAPI) {
        return new PersistenceManagerImpl(pikabuTestAPI);
    }

}
