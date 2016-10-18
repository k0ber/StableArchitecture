package com.nik.noveo.mvvm_dagger2.base;

import java.util.HashMap;

class DiManager {

    private static HashMap<Class, ComponentInjector> injectorProviders = new HashMap<>();
    private static AppComponent appComponent = DaggerAppComponent.builder().build();


    static void makeInjection(ComponentCreator creator, Class injectorClass, Object injectable) {
        ComponentInjector componentInjector = injectorProviders.get(injectorClass);
        if (componentInjector == null) {
            componentInjector = creator.create(appComponent);
            injectorProviders.put(componentInjector.getClass(), componentInjector);
        }
        componentInjector.inject(injectable);
    }

    static void release(Class componentInjectorClass) {
        injectorProviders.remove(componentInjectorClass);
    }
}
