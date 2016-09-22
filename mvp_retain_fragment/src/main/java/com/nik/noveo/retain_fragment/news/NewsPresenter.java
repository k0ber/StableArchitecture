package com.nik.noveo.retain_fragment.news;

import com.nik.noveo.retain_fragment.base.BasePresenter;

import rx.Subscription;

class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private NewsRepository repository;
    private Subscription loadSubscription;
    private NewsScreenState screenState;

    private class NewsScreenState {
        boolean loading;
        String newsText;

        NewsScreenState() {
            loading = false;
            newsText = "Nothing to show";
        }
    }

    NewsPresenter(NewsRepository repository) {
        this.repository = repository;
        screenState = new NewsScreenState();
    }

    @Override
    protected void onViewAttached() {
        if (viewReference.get() != null) {
            viewReference.get().setLoading(screenState.loading);
            viewReference.get().showText(screenState.newsText);
        }
    }

    @Override
    public void loadNews() {
        if (!screenState.loading) {
            updateLoadingState(true);
            loadSubscription = repository.getNews()
                    .doOnNext(this::updateNewsTextState)
                    .doOnTerminate(() -> updateLoadingState(false))
                    .subscribe();
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
            viewReference.get().showText(newsText);
        }
    }

    @Override
    public void onViewDied() {
        loadSubscription.unsubscribe();
    }

}
