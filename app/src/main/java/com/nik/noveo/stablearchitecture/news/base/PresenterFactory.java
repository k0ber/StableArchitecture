package com.nik.noveo.stablearchitecture.news.base;

public interface PresenterFactory {
    BasePresenter createPresenter();

    Class getPresenterClass();
}