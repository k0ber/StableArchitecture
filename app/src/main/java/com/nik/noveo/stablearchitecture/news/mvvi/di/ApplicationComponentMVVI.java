package com.nik.noveo.stablearchitecture.news.mvvi.di;

import com.nik.noveo.stablearchitecture.news.mvvi.NewsActivityMVVM;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModuleMVVI.class})
public interface ApplicationComponentMVVI {

    void inject(NewsActivityMVVM newsActivityMVVM);

}

