package com.nik.noveo.moxy.base;

import android.app.Application;

import com.nik.noveo.moxy.base.di.Injector;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initialize();
    }
}
