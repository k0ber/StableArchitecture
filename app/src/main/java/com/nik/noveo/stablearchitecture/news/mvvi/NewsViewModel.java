package com.nik.noveo.stablearchitecture.news.mvvi;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class NewsViewModel {

    @Inject
    NewsRepository newsRepository;


    @Inject
    public NewsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    //region ViewModel
    private BehaviorSubject<String> postSubject = BehaviorSubject.create("Nothing to show");
    private BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create(false);

    public Observable<String> postsObservable() {
        return postSubject.asObservable();
    }

    public Observable<Boolean> loadingObservable() {
        return loadingSubject.asObservable();
    }
    //endregion


    //region Business Logic
    public Observable<Void> loadNews() {
        if (loadingSubject.getValue()) {
            return Observable.empty();
        }

        loadingSubject.onNext(true);

        return newsRepository.getNews()
                .doOnNext(newsText -> postSubject.onNext(newsText))
                .doOnTerminate(() -> loadingSubject.onNext(false))
                .compose(RxUtils.hideType());
    }
    //endregion

}
