package com.nik.noveo.moxy.base.di;

import com.nik.noveo.moxy.base.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BuildersModule.class})
public interface BuildersComponent {
    void inject(App app);
}