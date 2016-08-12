package com.nik.noveo.stablearchitecture;

import android.app.Application;

import com.nik.noveo.stablearchitecture.news.mvvi.di.ApplicationComponentMVVI;
import com.nik.noveo.stablearchitecture.news.mvvi.di.DaggerApplicationComponentMVVI;


public class App extends Application {
    private ApplicationComponentMVVI applicationComponentMVVI;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponentMVVI = DaggerApplicationComponentMVVI
                .builder()
                .build();
    }

    public ApplicationComponentMVVI componentMVVI() {
        return applicationComponentMVVI;
    }

}