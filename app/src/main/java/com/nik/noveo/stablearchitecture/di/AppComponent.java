package com.nik.noveo.stablearchitecture.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface AppComponent {

    NewsComponent plus(NewsVMModule newsVMModule);
}