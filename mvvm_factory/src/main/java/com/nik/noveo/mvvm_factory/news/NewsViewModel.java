package com.nik.noveo.mvvm_factory.news;

import com.nik.noveo.mvvm_factory.base.ViewModel;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private CompositeSubscription subscriptions;

    NewsViewModel(NewsRepository newsRepository) {
        this.subscriptions = new CompositeSubscription();
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
        if (!loadingSubject.getValue()) {
            loadingSubject.onNext(true);
            subscriptions.add(newsRepository.getNews()
                    .doOnNext(newsText -> postSubject.onNext(newsText))
                    .doOnTerminate(() -> loadingSubject.onNext(false))
                    .subscribe());
        }
    }

    @Override
    protected void onViewDied() {
        subscriptions.unsubscribe();
    }
    //endregion
}
