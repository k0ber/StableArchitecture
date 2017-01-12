package com.nik.noveo.moxy.news;

import com.nik.noveo.moxy.utils.RxUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class NewsRepository {

    public Observable<String> getNews() {
        return Observable.timer(3, TimeUnit.SECONDS)
                .map(aLong -> "Breaking news! Count of victims increased to " + new Random().nextInt(1000))
                .compose(RxUtils.setSchedulers());
    }
}
