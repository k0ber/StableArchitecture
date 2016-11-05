package com.nik.noveo.mvvm_dagger2.base.di;

public interface ComponentBuilder<C extends dagger.MembersInjector> {
//    ComponentBuilder<C> activityModule();
    C build();
}