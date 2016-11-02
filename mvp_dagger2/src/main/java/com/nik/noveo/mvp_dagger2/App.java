package com.nik.noveo.mvp_dagger2;

import android.app.Application;

import com.nik.noveo.mvp_dagger2.di.AppComponent;
import com.nik.noveo.mvp_dagger2.di.DaggerAppComponent;
import com.nik.noveo.mvp_dagger2.di.NewsComponent;
import com.nik.noveo.mvp_dagger2.di.NewsModule;


public class App extends Application {

    private static volatile App appInstance;

    private NewsComponent newsComponent;
    private AppComponent appComponent;

    public static App get() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        appComponent = DaggerAppComponent.create();
    }

    public NewsComponent createNewsComponent() {
        newsComponent = appComponent.plus();
        return newsComponent;
    }

    public void releaseNewsComponent() {
        newsComponent = null;
    }

    public NewsComponent getNewsComponent() {
        return newsComponent;
    }
}