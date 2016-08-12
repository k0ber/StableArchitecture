package com.nik.noveo.stablearchitecture.news.mvp;

public interface NewsContract {

    interface Presenter {
        void onViewAttached();
        void loadNews();
    }

    interface View {
        void setPresenter(Presenter presenter);
        void setLoading(boolean isLoading);
        void setNewsText(String text);
    }

}