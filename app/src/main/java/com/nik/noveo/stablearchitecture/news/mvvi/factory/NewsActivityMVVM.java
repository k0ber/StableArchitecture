package com.nik.noveo.stablearchitecture.news.mvvi.factory;

import android.os.Bundle;

import com.nik.noveo.stablearchitecture.news.NewsActivity;
import com.nik.noveo.stablearchitecture.news.base.PresenterCache;


public class NewsActivityMVVM extends NewsActivity {

    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsVM = (NewsVM) PresenterCache.get(NewsVMFactory.getInstance());
        initBindings();
    }

    private void initBindings() {
        subscriptions.addAll(
                newsVM.postsObservable().subscribe(this::setNewsText),
                newsVM.loadingObservable().subscribe(this::setLoading)
        );
    }

    @Override
    public void loadClicked() {
        newsVM.loadNews();
    }

    @Override
    protected void onFinish() {
        PresenterCache.remove(NewsVM.class);
    }
}
