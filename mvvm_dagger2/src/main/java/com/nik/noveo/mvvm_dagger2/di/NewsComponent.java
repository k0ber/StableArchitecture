package com.nik.noveo.mvvm_dagger2.di;



import com.nik.noveo.mvvm_dagger2.news.NewsView;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsVMModule.class)
public interface NewsComponent {

    void inject(NewsView newsActivityView);
}

