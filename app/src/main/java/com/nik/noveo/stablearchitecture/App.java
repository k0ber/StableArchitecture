package com.nik.noveo.stablearchitecture;

import android.app.Application;

import com.nik.noveo.stablearchitecture.news.mvp.di.ApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.DaggerApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvvi.di.ApplicationComponentMVVI;
import com.nik.noveo.stablearchitecture.news.mvvi.di.DaggerApplicationComponentMVVI;


public class App extends Application {
    private ApplicationComponentMVVI applicationComponentMVVI;
    private ApplicationComponentMVP applicationComponentMVP;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponentMVVI = DaggerApplicationComponentMVVI
                .builder()
                .build();

        applicationComponentMVP = DaggerApplicationComponentMVP
                .builder()
                .build();
    }

    public ApplicationComponentMVVI componentMVVI() {
        return applicationComponentMVVI;
    }

    public ApplicationComponentMVP componentMVP() {
        return applicationComponentMVP;
    }



}