package com.nik.noveo.mvvm_dagger2.base;

public interface ComponentCreator<T extends ComponentInjector> {
    T create(AppComponent appComponent);
}