package com.nik.noveo.mvvm_dagger2.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface AppComponent {

    NewsComponent plus(NewsVMModule newsVMModule);
}