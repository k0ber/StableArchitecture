package com.nik.noveo.stablearchitecture.news;

import android.os.Bundle;

import javax.inject.Inject;


public class NewsActivityMVP extends NewsActivity implements NewsContract.View {

    @Inject NewsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewsPresenter(new NewsRepository(), this);
        presenter.onViewAttached();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadClicked() {
        subscriptions.add(presenter.loadNews().subscribe());
    }

    @Override
    public void setLoading(boolean isLoading) {
        super.setLoading(isLoading);
    }

    @Override
    public void setNewsText(String text) {
        super.setNewsText(text);
    }
}
