package com.nik.noveo.moxy.news.di;

import com.nik.noveo.moxy.base.di.ComponentBuilder;
import com.nik.noveo.moxy.news.NewsActivity;

import dagger.MembersInjector;
import dagger.Subcomponent;

@Subcomponent(modules = NewsModule.class)
public interface NewsViewComponent extends MembersInjector<NewsActivity> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<NewsViewComponent> {
    }
}
