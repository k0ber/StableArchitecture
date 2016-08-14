package com.nik.noveo.stablearchitecture.news.mvp;

import android.os.Bundle;

import com.nik.noveo.stablearchitecture.App;
import com.nik.noveo.stablearchitecture.news.NewsActivity;

import javax.inject.Inject;


public class NewsActivityMVP extends NewsActivity implements NewsContract.View {

    @Inject
    NewsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).componentMVP(this).inject(this);
        presenter.onViewAttached();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadClicked() {
        presenter.loadNews();
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
