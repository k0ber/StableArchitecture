package com.nik.noveo.stablearchitecture.di;


import com.nik.noveo.stablearchitecture.news.NewsView;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsVMModule.class)
public interface NewsComponent {

    void inject(NewsView newsActivityView);
}

