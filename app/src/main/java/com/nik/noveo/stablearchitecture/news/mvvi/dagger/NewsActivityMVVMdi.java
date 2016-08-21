package com.nik.noveo.stablearchitecture.news.mvvi.dagger;

import android.os.Bundle;

import com.nik.noveo.stablearchitecture.App;
import com.nik.noveo.stablearchitecture.news.NewsActivity;

import javax.inject.Inject;

public class NewsActivityMVVMdi extends NewsActivity {

    @Inject
    NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).componentMVVI().inject(this);
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
        newsVM.release();
    }
}
