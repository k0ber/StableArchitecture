package com.nik.noveo.mvvm_dagger2.base.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                // other singleton modules if needed
                RootModule.class
        })
public interface RootComponent {
    void inject(DiManager target);
}