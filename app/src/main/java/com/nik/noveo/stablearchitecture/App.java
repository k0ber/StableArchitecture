package com.nik.noveo.stablearchitecture;

import android.app.Application;

import com.nik.noveo.stablearchitecture.news.mvp.NewsContract;
import com.nik.noveo.stablearchitecture.news.mvp.di.ApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.DaggerApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.NewsPresenterModule;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.ApplicationComponentMVVI;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.DaggerApplicationComponentMVVI;


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

    public ApplicationComponentMVP componentMVP(NewsContract.View view) {
        return DaggerApplicationComponentMVP
                .builder()
                .newsPresenterModule(new NewsPresenterModule(view))
                .build();
    }
}