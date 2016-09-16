package com.nik.noveo.mvp_dagger2.di;


import com.nik.noveo.mvp_dagger2.news.NewsActivityMVP;

import dagger.Component;

@Component(modules = NewsPresenterModule.class)
public interface ApplicationComponentMVP {

    void inject(NewsActivityMVP newsActivityMVP);
}
