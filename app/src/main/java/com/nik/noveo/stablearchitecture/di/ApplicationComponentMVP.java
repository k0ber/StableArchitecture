package com.nik.noveo.stablearchitecture.di;

import com.nik.noveo.stablearchitecture.news.NewsActivityMVP;

import dagger.Component;

@Component(modules = NewsPresenterModule.class)
public interface ApplicationComponentMVP {

    void inject(NewsActivityMVP newsActivityMVP);
}
