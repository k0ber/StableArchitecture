package com.nik.noveo.stablearchitecture.news.mvvi.dagger.di;


import com.nik.noveo.stablearchitecture.news.mvvi.dagger.BaseActivityView;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsVMModule.class)
public interface NewsVMComponent {

    void inject(BaseActivityView newsActivityView);
}

