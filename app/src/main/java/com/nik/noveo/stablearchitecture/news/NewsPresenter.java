package com.nik.noveo.stablearchitecture.news;

import com.nik.noveo.stablearchitecture.base.BasePresenter;
import com.nik.noveo.stablearchitecture.base.BaseView;

import rx.subscriptions.CompositeSubscription;


public class NewsPresenter implements NewsContract.Presenter, BasePresenter {

    private NewsRepository newsRepository;
    private CompositeSubscription subscriptions;

    private NewsContract.View view;
    private NewsScreenState screenState;

    private class NewsScreenState {
        boolean loading;
        String newsText;

        NewsScreenState() {
            loading = false;
            newsText = "Nothing to show";
        }
    }

    public NewsPresenter(NewsRepository newsRepository) {
        subscriptions = new CompositeSubscription();
        screenState = new NewsScreenState();
        this.newsRepository = newsRepository;
    }

    @Override
    public void onViewReady(BaseView view) {
        this.view = (NewsContract.View) view;
        updateViewState();
    }

    private void updateViewState() {
        view.setLoading(screenState.loading);
        view.setNewsText(screenState.newsText);
    }

    @Override
    public void onViewDied() {
        subscriptions.unsubscribe();
    }

    @Override
    public void loadNews() {
        updateLoadingState(true);
        subscriptions.add(newsRepository.getNews()
                .doOnNext(this::updateNewsTextState)
                .doOnTerminate(() -> updateLoadingState(false))
                .subscribe());
    }

    private void updateLoadingState(boolean loading) {
        screenState.loading = loading;
        if (view != null) {
            view.setLoading(loading);
        }
    }

    private void updateNewsTextState(String newsText) {
        screenState.newsText = newsText;
        if (view != null) {
            view.setNewsText(newsText);
        }
    }
}