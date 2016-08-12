package com.nik.noveo.stablearchitecture.news.mvvi;

import android.os.Bundle;

import com.nik.noveo.stablearchitecture.App;
import com.nik.noveo.stablearchitecture.news.NewsActivity;

import javax.inject.Inject;

public class NewsActivityMVVM extends NewsActivity {

    @Inject
    NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).componentMVVI().inject(this);
        initBindings();
    }

    private void initBindings() {
        subscriptions.addAll(
                newsViewModel.postsObservable().subscribe(this::setNewsText),
                newsViewModel.loadingObservable().subscribe(this::setLoading)
        );
    }

    @Override
    public void loadClicked() {
        subscriptions.add(newsViewModel.loadNews().subscribe());
    }

}
