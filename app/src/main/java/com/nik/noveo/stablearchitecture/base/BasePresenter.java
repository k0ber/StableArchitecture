package com.nik.noveo.stablearchitecture.base;

public interface BasePresenter {
    void onViewReady(BaseView view);

    void onViewDied();
}