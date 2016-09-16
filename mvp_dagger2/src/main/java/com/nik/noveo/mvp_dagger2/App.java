package com.nik.noveo.mvp_dagger2;

import android.app.Application;

import com.nik.noveo.mvp_dagger2.di.ApplicationComponentMVP;
import com.nik.noveo.mvp_dagger2.di.DaggerApplicationComponentMVP;
import com.nik.noveo.mvp_dagger2.di.NewsPresenterModule;
import com.nik.noveo.mvp_dagger2.news.NewsContract;


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