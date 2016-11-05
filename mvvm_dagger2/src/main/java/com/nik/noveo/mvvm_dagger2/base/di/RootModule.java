package com.nik.noveo.mvvm_dagger2.base.di;


import com.nik.noveo.mvvm_dagger2.news.NewsActivityView;
import com.nik.noveo.mvvm_dagger2.news.di.NewsComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(
        subcomponents = {
                NewsComponent.class,
                //AnotherComponent.class
        })
abstract class RootModule {

    @Binds
    @IntoMap
    @ViewKey(NewsActivityView.class)
    public abstract ComponentBuilder newsActivityComponentBuilder(NewsComponent.Builder impl);

    // AnotherComponentBuilder Binding
}