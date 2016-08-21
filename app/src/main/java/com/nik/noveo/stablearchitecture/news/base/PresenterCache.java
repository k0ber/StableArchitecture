package com.nik.noveo.stablearchitecture.news.base;

import android.support.v4.util.SimpleArrayMap;

public class PresenterCache {

    private static SimpleArrayMap<Class, BasePresenter> presenters;

    public static BasePresenter get(PresenterFactory presenterFactory) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }

        Class presenterClass = presenterFactory.getPresenterClass();
        BasePresenter presenter = presenters.get(presenterClass);
        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            presenters.put(presenterClass, presenter);
        }
        return presenter;
    }

    public static void remove(Class presenterClass) {
        if (presenters != null) {
            BasePresenter removedPresenter = presenters.remove(presenterClass);
            if (removedPresenter != null) {
                removedPresenter.release();
            }
        }
    }
}
