package com.nik.noveo.mvvm_factory.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {

    public static <T> Observable.Transformer<T, T> setSchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
