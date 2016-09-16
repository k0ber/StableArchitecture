package com.nik.noveo.mvp_dagger2.news;

import rx.Observable;

public interface NewsContract {

    interface Presenter {
        void onViewAttached();

        Observable<Void> loadNews();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void setLoading(boolean isLoading);

        void setNewsText(String text);
    }
}