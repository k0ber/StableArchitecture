package com.nik.noveo.mvvm_dagger2.base.framework;

import android.app.Application;

import com.nik.noveo.mvvm_dagger2.base.di.DaggerRootComponent;
import com.nik.noveo.mvvm_dagger2.base.di.DiManager;

public class App extends Application {

    private DiManager diManager;

    @Override
    public void onCreate() {
        super.onCreate();
        diManager = new DiManager();
        DaggerRootComponent.create().inject(diManager);
    }

    DiManager getDiManager() {
        return diManager;
    }
}
