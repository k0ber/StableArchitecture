package com.nik.noveo.mvp_factory.base;

public interface PresenterFactory<P extends BasePresenter> {
    P createPresenter();
}