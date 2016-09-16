package com.nik.noveo.mvvm_factory.base;

public interface PresenterFactory {
    BasePresenter createPresenter();

    Class getPresenterClass();
}