package com.nik.noveo.stablearchitecture.news.mvvi.dagger;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.base.BasePresenter;
import com.nik.noveo.stablearchitecture.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class NewsVM implements BasePresenter {

    @Inject
    NewsRepository newsRepository;
    private CompositeSubscription subscriptions;

    @Inject
    public NewsVM(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    //region ViewModel
    private BehaviorSubject<String> postSubject = BehaviorSubject.create("Nothing to show");
    private BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create(false);

    Observable<String> postsObservable() {
        return postSubject.asObservable();
    }

    Observable<Boolean> loadingObservable() {
        return loadingSubject.asObservable();
    }
    //endregion

    //region Business Logic
    void loadNews() {
        if (loadingSubject.getValue()) {
            return;
        }

        loadingSubject.onNext(true);

        subscriptions.add(newsRepository.getNews()
                .doOnNext(newsText -> postSubject.onNext(newsText))
                .doOnTerminate(() -> loadingSubject.onNext(false))
                .compose(RxUtils.hideType())
                .subscribe());
    }

    @Override
    public void release() {
        subscriptions.unsubscribe();
    }
    //endregion
}
