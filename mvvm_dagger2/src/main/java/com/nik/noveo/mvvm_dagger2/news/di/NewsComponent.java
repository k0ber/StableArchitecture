package com.nik.noveo.mvvm_dagger2.news.di;

import com.nik.noveo.mvvm_dagger2.base.ComponentInjector;
import com.nik.noveo.mvvm_dagger2.news.NewsActivityView;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsVMModule.class)
public interface NewsComponent extends ComponentInjector<NewsActivityView> {
    @Override
    void inject(NewsActivityView newsActivityView);
}
