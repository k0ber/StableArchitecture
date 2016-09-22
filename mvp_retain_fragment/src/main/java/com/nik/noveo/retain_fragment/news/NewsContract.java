package com.nik.noveo.retain_fragment.news;


public interface NewsContract {

    interface View {
        void setLoading(boolean loading);
        void showText(String text);
    }

    interface Presenter {
        void loadNews();
    }
}
