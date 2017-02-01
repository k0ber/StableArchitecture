package com.nik.noveo.moxy.news.di;

import com.nik.noveo.moxy.base.di.ComponentBuilder;
import com.nik.noveo.moxy.news.NewsPresenter;

import dagger.MembersInjector;
import dagger.Subcomponent;

@Subcomponent(modules = NewsModule.class)
public interface NewsPresenterComponent extends MembersInjector<NewsPresenter> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<NewsPresenterComponent> {
    }
}
