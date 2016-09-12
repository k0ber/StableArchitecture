package com.nik.noveo.stablearchitecture.news;

import com.nik.noveo.stablearchitecture.base.BasePresenter;
import com.nik.noveo.stablearchitecture.base.BaseView;

public interface NewsContract {

    interface Presenter extends BasePresenter {
        void loadNews();
    }

    interface View extends BaseView {
        void setLoading(boolean isLoading);

        void setNewsText(String text);
    }
}