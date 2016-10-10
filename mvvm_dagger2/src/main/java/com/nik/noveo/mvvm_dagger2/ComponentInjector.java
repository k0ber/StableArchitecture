package com.nik.noveo.mvvm_dagger2;

public interface ComponentInjector<T> {
    void inject(T t);
}
