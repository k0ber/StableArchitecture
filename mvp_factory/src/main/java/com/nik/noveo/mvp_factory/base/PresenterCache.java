package com.nik.noveo.mvp_factory.base;

import android.support.v4.util.SimpleArrayMap;

class PresenterCache {

    private static SimpleArrayMap<Class, BasePresenter> presenters;

    static BasePresenter get(Class presenterClass, PresenterFactory presenterFactory) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }

        BasePresenter presenter = presenters.get(presenterClass);
        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            presenters.put(presenterClass, presenter);
        }
        return presenter;
    }

    static void remove(Class presenterClass) {
        if (presenters != null) {
            BasePresenter removedPresenter = presenters.remove(presenterClass);
            if (removedPresenter != null) {
                removedPresenter.onViewDied();
            }
        }
    }
}
