package com.nik.noveo.moxy.base.di;



import com.nik.noveo.moxy.news.NewsActivity;
import com.nik.noveo.moxy.news.di.NewsComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(
        subcomponents = {
                NewsComponent.class,
                //AnotherComponent.class
        })
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ViewKey(NewsActivity.class)
    public abstract ComponentBuilder newsActivityComponentBuilder(NewsComponent.Builder impl);

    // AnotherComponentBuilder Binding
}