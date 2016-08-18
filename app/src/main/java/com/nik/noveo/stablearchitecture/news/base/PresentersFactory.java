package com.nik.noveo.stablearchitecture.news.base;

class PresentersFactory {

    @SuppressWarnings("all")
    public static BasePresenter create(Class<? extends BasePresenter> presenterClass) {
        BasePresenter presenter = null;
        try {
            presenter = presenterClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return presenter;
    }
}