package com.nik.noveo.moxy.news;

import com.arellomobile.mvp.MvpView;

interface NewsView extends MvpView {

    void showProgress(boolean visible);

    void showMessage(String message);
}