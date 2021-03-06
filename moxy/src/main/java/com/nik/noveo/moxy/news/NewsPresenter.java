package com.nik.noveo.moxy.news;

import com.arellomobile.mvp.InjectViewState;
import com.nik.noveo.moxy.base.BasePresenter;

import javax.inject.Inject;


@InjectViewState
public class NewsPresenter extends BasePresenter<NewsView> {

    @Inject
    NewsRepository newsRepository;

    void loadNews() {

        //  todo if loading => return
        getViewState().showProgress(true);

        unsubscribeOnDestroy(newsRepository.getNews()
                .doOnNext(newsText -> getViewState().showMessage(newsText))
                .doOnTerminate(() -> getViewState().showProgress(false))
                .subscribe());
    }

}
