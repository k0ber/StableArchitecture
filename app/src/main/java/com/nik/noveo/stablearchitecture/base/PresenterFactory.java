package com.nik.noveo.stablearchitecture.base;

interface PresenterFactory {
    BasePresenter createPresenter();

    Class getPresenterClass();
}