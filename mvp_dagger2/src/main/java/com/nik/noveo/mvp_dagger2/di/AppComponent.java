package com.nik.noveo.mvp_dagger2.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface AppComponent {
    NewsComponent plus(NewsModule module);
}
