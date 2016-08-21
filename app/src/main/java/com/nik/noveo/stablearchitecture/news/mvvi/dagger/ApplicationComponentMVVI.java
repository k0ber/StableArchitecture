package com.nik.noveo.stablearchitecture.news.mvvi.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModuleMVVI.class})
public interface ApplicationComponentMVVI {

    void inject(NewsActivityMVVMdi newsActivityMVVMdi);
}

