package com.nik.noveo.moxy.base.di;

import com.nik.noveo.moxy.news.NewsActivity;
import com.nik.noveo.moxy.news.NewsPresenter;
import com.nik.noveo.moxy.news.di.NewsPresenterComponent;
import com.nik.noveo.moxy.news.di.NewsViewComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(
        subcomponents = {
                NewsViewComponent.class,
                NewsPresenterComponent.class,
                //AnotherComponent.class
        })
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ViewKey(NewsActivity.class)
    public abstract ComponentBuilder newsActivityComponentBuilder(NewsViewComponent.Builder impl);

    @Binds
    @IntoMap
    @ViewKey(NewsPresenter.class)
    public abstract ComponentBuilder newsPresenterComponentBuilder(NewsPresenterComponent.Builder impl);

    // AnotherComponentBuilder Binding
}