package com.nik.noveo.stablearchitecture.base;

public interface PresenterFactory<P extends BasePresenter> {
    P createPresenter();
}