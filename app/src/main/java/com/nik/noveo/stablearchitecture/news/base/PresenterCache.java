package com.nik.noveo.stablearchitecture.news.base;

import android.support.v4.util.SimpleArrayMap;

class PresenterCache {

    private static SimpleArrayMap<Class, BasePresenter> presenters;

    private PresenterCache() {
    }

    static BasePresenter get(Class<? extends BasePresenter> presenterClass) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }

        BasePresenter presenter = presenters.get(presenterClass);
        if (presenter == null) {
            presenter = PresentersFactory.create(presenterClass);
            presenters.put(presenterClass, presenter);
        }
        return presenter;
    }

    static void remove(Class presenterClass) {
        if (presenters != null) {
            presenters.remove(presenterClass);
        }
    }
}
