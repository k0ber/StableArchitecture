package com.nik.noveo.stablearchitecture.news;

import com.nik.noveo.stablearchitecture.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsRepository newsRepository;
    private NewsContract.View view;

    public NewsPresenter(NewsRepository newsRepository, NewsContract.View view) {
        this.newsRepository = newsRepository;
        this.view = view;
    }

    @Override
    public void onViewAttached() {
        view.setPresenter(this);
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
