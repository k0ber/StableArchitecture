package com.nik.noveo.mvvm_dagger2.news.di;

import com.nik.noveo.mvvm_dagger2.base.di.ComponentBuilder;
import com.nik.noveo.mvvm_dagger2.news.NewsActivityView;

import dagger.MembersInjector;
import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsVMModule.class)
public interface NewsComponent extends MembersInjector<NewsActivityView> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<NewsComponent> {
    }

}
