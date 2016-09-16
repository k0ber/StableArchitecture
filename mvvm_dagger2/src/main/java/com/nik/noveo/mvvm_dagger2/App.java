package com.nik.noveo.mvvm_dagger2;

import android.app.Application;

import com.nik.noveo.mvvm_dagger2.di.AppComponent;
import com.nik.noveo.mvvm_dagger2.di.DaggerAppComponent;
import com.nik.noveo.mvvm_dagger2.di.NewsComponent;
import com.nik.noveo.mvvm_dagger2.di.NewsVMModule;


public class App extends Application {

    private static volatile App appInstance;

    private AppComponent appComponent;
    private NewsComponent newsComponent;

    public static App get() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        appComponent = DaggerAppComponent.builder().build();
    }

    public NewsComponent createNewsComponent() {
        newsComponent = appComponent.plus(new NewsVMModule());
        return newsComponent;
    }

    public void releaseNewsComponent() {
        newsComponent = null;
    }

    public NewsComponent getNewsComponent() {
        return newsComponent;
    }
}