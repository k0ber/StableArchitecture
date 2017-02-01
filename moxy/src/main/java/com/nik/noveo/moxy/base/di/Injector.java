package com.nik.noveo.moxy.base.di;

import java.util.Map;

import javax.inject.Inject;

import dagger.MembersInjector;

public class Injector {

    private static final Injector INSTANCE = new Injector();

    @Inject
    Map<Class, ComponentBuilder> componentBuilders;

    public static void initialize() {
        DaggerBuildersComponent.create().inject(INSTANCE);
    }

    @SuppressWarnings("unchecked")
    public static void inject(Object target) {
        Class targetClass = target.getClass();
        ComponentBuilder builder = INSTANCE.componentBuilders.get(targetClass);
        MembersInjector componentInjector = builder.build();
        componentInjector.injectMembers(target);
    }

}
