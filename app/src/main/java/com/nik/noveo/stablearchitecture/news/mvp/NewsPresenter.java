package com.nik.noveo.stablearchitecture.news.mvp;

import com.nik.noveo.stablearchitecture.news.NewsRepository;

import javax.inject.Inject;

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
    public void loadNews() {
        view.setLoading(true);
        newsRepository.getNews()
                .doOnNext(s -> {
                    if (view != null) view.setNewsText(s);
                })
                .doOnTerminate(() -> {
                    if (view != null) view.setLoading(false);
                })
                .subscribe();
    }

}
