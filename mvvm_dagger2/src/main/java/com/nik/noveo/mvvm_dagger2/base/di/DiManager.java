package com.nik.noveo.mvvm_dagger2.base.di;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.MembersInjector;

public class DiManager {

    private HashMap<Class, MembersInjector> components = new HashMap<>();
    @Inject Map<Class, ComponentBuilder> componentBuilders;


    public void makeInjection(Object target) {
        Class targetClass = target.getClass();
        MembersInjector componentInjector = components.get(targetClass);
        if (componentInjector == null) {
            ComponentBuilder builder = componentBuilders.get(targetClass);
            componentInjector = builder.build();
            components.put(targetClass, componentInjector);
        }
        componentInjector.injectMembers(target);
    }

    public void release(Object target) {
        components.remove(target.getClass());
    }
}
