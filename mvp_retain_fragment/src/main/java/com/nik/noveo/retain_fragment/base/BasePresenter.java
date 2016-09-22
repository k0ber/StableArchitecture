package com.nik.noveo.retain_fragment.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {

    protected WeakReference<V> viewReference;

    void attachView(V view) {
        viewReference = new WeakReference<>(view);
        onViewAttached();
    }

    protected abstract void onViewAttached();

    protected abstract void onViewDied();
}