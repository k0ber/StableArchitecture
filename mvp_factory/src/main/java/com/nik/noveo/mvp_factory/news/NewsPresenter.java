package com.nik.noveo.mvp_factory.news;


import com.nik.noveo.mvp_factory.base.BasePresenter;

import rx.subscriptions.CompositeSubscription;


class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private NewsRepository newsRepository;
    private CompositeSubscription subscriptions;

    private NewsScreenState screenState;

    private class NewsScreenState {
        boolean loading;
        String newsText;

        NewsScreenState() {
            loading = false;
            newsText = "Nothing to show";
        }
    }

    NewsPresenter(NewsRepository newsRepository) {
        subscriptions = new CompositeSubscription();
        screenState = new NewsScreenState();
        this.newsRepository = newsRepository;
    }

    @Override
    protected void onViewAttached() {
        updateViewState();
    }

    private void updateViewState() {
        if (viewReference.get() != null) {
            viewReference.get().setLoading(screenState.loading);
            viewReference.get().setNewsText(screenState.newsText);
        }
    }

    @Override
    protected void onViewDied() {
        subscriptions.unsubscribe();
    }

    @Override
    public void loadNews() {
        if (!screenState.loading) {
            updateLoadingState(true);
            subscriptions.add(newsRepository.getNews()
                    .doOnNext(this::updateNewsTextState)
                    .doOnTerminate(() -> updateLoadingState(false))
                    .subscribe());
        }
    }

    private void updateLoadingState(boolean loading) {
        screenState.loading = loading;
        if (viewReference.get() != null) {
            viewReference.get().setLoading(loading);
        }
    }

    private void updateNewsTextState(String newsText) {
        screenState.newsText = newsText;
        if (viewReference.get() != null) {
            viewReference.get().setNewsText(newsText);
        }
    }
}