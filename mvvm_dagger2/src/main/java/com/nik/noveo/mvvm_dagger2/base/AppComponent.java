package com.nik.noveo.mvvm_dagger2.base;

import com.nik.noveo.mvvm_dagger2.news.di.NewsComponent;
import com.nik.noveo.mvvm_dagger2.news.di.NewsVMModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface AppComponent {
    NewsComponent plus(NewsVMModule newsVMModule);

    // here should be methods to create others subcomponents
}