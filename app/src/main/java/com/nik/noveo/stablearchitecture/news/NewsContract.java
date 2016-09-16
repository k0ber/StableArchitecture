package com.nik.noveo.stablearchitecture.news;


interface NewsContract {

    interface Presenter {
        void loadNews();
    }

    interface View {
        void setLoading(boolean isLoading);

        void setNewsText(String text);
    }
}