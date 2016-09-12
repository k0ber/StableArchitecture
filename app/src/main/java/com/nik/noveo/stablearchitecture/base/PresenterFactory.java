package com.nik.noveo.stablearchitecture.base;

public interface PresenterFactory {
    BasePresenter createPresenter();

    Class getPresenterClass();
}