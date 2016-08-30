package com.nik.noveo.stablearchitecture.news.mvvi.dagger.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface AppComponent {

    NewsVMComponent plus(NewsVMModule newsVMModule);

}