package com.nik.noveo.stablearchitecture.news.mvp.di;

import com.nik.noveo.stablearchitecture.news.mvp.NewsActivityMVP;

import dagger.Component;

@Component(modules = NewsPresenterModule.class)
public interface ApplicationComponentMVP {

    void inject(NewsActivityMVP newsActivityMVP);
}
