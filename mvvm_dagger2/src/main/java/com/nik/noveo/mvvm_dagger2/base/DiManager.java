package com.nik.noveo.mvvm_dagger2.base;

import com.nik.noveo.mvvm_dagger2.ComponentInjector;
import com.nik.noveo.mvvm_dagger2.di.AppComponent;
import com.nik.noveo.mvvm_dagger2.di.DaggerAppComponent;
import com.nik.noveo.mvvm_dagger2.di.NewsComponent;
import com.nik.noveo.mvvm_dagger2.di.NewsVMModule;
import com.nik.noveo.mvvm_dagger2.news.NewsActivityView;

public class DiManager {


    private static AppComponent appComponent = DaggerAppComponent.builder().build();
    private static NewsComponent newsComponent;


    public static ComponentInjector getInjector(Class injectorClass) {
        newsComponent = appComponent.plus(new NewsVMModule());
        return newsComponent;
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
