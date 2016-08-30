package com.nik.noveo.stablearchitecture;

import android.app.Application;

import com.nik.noveo.stablearchitecture.news.mvp.NewsContract;
import com.nik.noveo.stablearchitecture.news.mvp.di.ApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.DaggerApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.NewsPresenterModule;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.di.AppComponent;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.di.DaggerAppComponent;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.di.NewsVMComponent;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.di.NewsVMModule;


public class App extends Application {

    private static volatile App appInstance;

    private AppComponent appComponent;
    private NewsVMComponent newsVMComponent;

    public static App get() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        appComponent = DaggerAppComponent.builder().build();
    }

    public NewsVMComponent createNewsVMComponent() {
        newsVMComponent = appComponent.plus(new NewsVMModule());
        return newsVMComponent;
    }

    public void releaseNewsVMComponent() {
        newsVMComponent = null;
    }

    public NewsVMComponent getNewsVMComponent() {
        return newsVMComponent;
    }

    // MVP
    public ApplicationComponentMVP componentMVP(NewsContract.View view) {
        return DaggerApplicationComponentMVP
                .builder()
                .newsPresenterModule(new NewsPresenterModule(view))
                .build();
    }
}