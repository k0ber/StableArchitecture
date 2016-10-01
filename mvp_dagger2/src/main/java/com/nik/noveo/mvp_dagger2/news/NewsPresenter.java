package com.nik.noveo.mvp_dagger2.news;


import com.nik.noveo.mvp_dagger2.base.BasePresenter;
import com.nik.noveo.mvp_dagger2.utils.RxUtils;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private NewsRepository newsRepository;
    private CompositeSubscription subscriptions;
    private NewsState state;

    @Inject
    public NewsPresenter(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        this.subscriptions = new CompositeSubscription();
        this.state = new NewsState();
    }

    private class NewsState {
        String text;
        boolean loading;

        NewsState() {
            text = "Nothing to show";
            loading = false;
        }
    }

    @Override
    public void onViewAttached() {
        if (viewReference.get() != null) {
            viewReference.get().setLoading(state.loading);
            viewReference.get().setNewsText(state.text);
        }
    }

    @Override
    public void loadNews() {
        if (!state.loading) {
            changeLoading(true);
            subscriptions.add(newsRepository.getNews()
                    .doOnNext(this::updateText)
                    .doOnTerminate(() -> changeLoading(false))
                    .compose(RxUtils.hideType())
                    .subscribe());
        }
    }

    @Override
    protected void onViewDied() {
        subscriptions.unsubscribe();
    }

    private void updateText(String text) {
        state.text = text;
        if (viewReference.get() != null) {
            viewReference.get().setNewsText(text);
        }
    }

    private void changeLoading(boolean isLoading) {
        state.loading = isLoading;
        if (viewReference.get() != null) {
            viewReference.get().setLoading(isLoading);
        }
    }
}
