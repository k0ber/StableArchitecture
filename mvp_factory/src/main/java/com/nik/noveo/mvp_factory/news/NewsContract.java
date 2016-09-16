package com.nik.noveo.mvp_factory.news;


interface NewsContract {

    interface Presenter {
        void loadNews();
    }

    interface View {
        void setLoading(boolean isLoading);

        void setNewsText(String text);
    }
}