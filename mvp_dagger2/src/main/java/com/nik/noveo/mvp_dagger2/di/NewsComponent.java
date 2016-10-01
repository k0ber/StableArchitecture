package com.nik.noveo.mvp_dagger2.di;

import com.nik.noveo.mvp_dagger2.news.NewsActivityView;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsModule.class)
public interface NewsComponent {
    void inject(NewsActivityView newsActivityView);
}