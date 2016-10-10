package com.nik.noveo.mvvm_dagger2.news;

import com.nik.noveo.mvvm_dagger2.base.ViewModel;
import com.nik.noveo.mvvm_dagger2.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class NewsViewModel implements ViewModel{

    private NewsRepository newsRepository;
    private CompositeSubscription subscriptions;

    @Inject
    public NewsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        subscriptions = new CompositeSubscription();
    }

    private BehaviorSubject<String> postSubject = BehaviorSubject.create("Nothing to show");
    private BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create(false);

    Observable<String> postsObservable() {
        return postSubject.asObservable();
    }

    Observable<Boolean> loadingObservable() {
        return loadingSubject.asObservable();
    }

    // busyness logic
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
}
