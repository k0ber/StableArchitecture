package com.nik.noveo.moxy.base.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BuildersModule.class})
interface BuildersComponent {
    void inject(Injector injector);
}