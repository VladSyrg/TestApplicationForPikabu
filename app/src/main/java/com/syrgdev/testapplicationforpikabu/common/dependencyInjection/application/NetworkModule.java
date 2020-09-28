package com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application;

import com.syrgdev.testapplicationforpikabu.network.PikabuTestAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.syrgdev.testapplicationforpikabu.Constants.BASE_URL;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    PikabuTestAPI getTestPikabuAPI(Retrofit retrofit) {
        return retrofit.create(PikabuTestAPI.class);
    }

}
