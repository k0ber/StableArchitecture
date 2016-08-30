package com.nik.noveo.stablearchitecture;

import android.app.Application;

import com.nik.noveo.stablearchitecture.di.ApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.di.DaggerApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.di.NewsPresenterModule;
import com.nik.noveo.stablearchitecture.news.NewsContract;


public class App extends Application {

    private static volatile App appInstance;

    public static App get() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public ApplicationComponentMVP componentMVP(NewsContract.View view) {
        return DaggerApplicationComponentMVP
                .builder()
                .newsPresenterModule(new NewsPresenterModule(view))
                .build();
    }
}