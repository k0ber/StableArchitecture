package com.nik.noveo.moxy.base;

import android.app.Application;

import com.nik.noveo.moxy.base.di.ComponentBuilder;
import com.nik.noveo.moxy.base.di.DaggerBuildersComponent;

import java.util.Map;

import javax.inject.Inject;

import dagger.MembersInjector;


public class App extends Application {

    @Inject
    Map<Class, ComponentBuilder> componentBuilders;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerBuildersComponent.create().inject(this);
    }

    @SuppressWarnings("unchecked")
    public void inject(Object target) {
        Class targetClass = target.getClass();
        ComponentBuilder builder = componentBuilders.get(targetClass);
        MembersInjector componentInjector = builder.build();
        componentInjector.injectMembers(target);
    }

}
