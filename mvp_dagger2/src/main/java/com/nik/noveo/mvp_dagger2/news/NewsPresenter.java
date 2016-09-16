package com.nik.noveo.mvp_dagger2.news;


import com.nik.noveo.mvp_dagger2.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsRepository newsRepository;
    private NewsContract.View view;

    @Inject
    public NewsPresenter(NewsRepository newsRepository, NewsContract.View view) {
        this.newsRepository = newsRepository;
        this.view = view;
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void onViewAttached() {
        view.setLoading(false);
        view.setNewsText("Nothing to show");
    }

    @Override
    public Observable<Void> loadNews() {
        view.setLoading(true);

        return newsRepository.getNews()
                .doOnNext(s -> view.setNewsText(s))
                .doOnTerminate(() -> view.setLoading(false))
                .compose(RxUtils.hideType());
    }
}
