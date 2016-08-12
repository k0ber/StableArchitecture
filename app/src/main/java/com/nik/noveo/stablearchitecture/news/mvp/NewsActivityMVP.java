package com.nik.noveo.stablearchitecture.news.mvp;

import android.os.Bundle;

import com.nik.noveo.stablearchitecture.news.NewsActivity;
import com.nik.noveo.stablearchitecture.news.mvp.di.DaggerApplicationComponentMVP;
import com.nik.noveo.stablearchitecture.news.mvp.di.NewsPresenterModule;


public class NewsActivityMVP extends NewsActivity implements NewsContract.View {

    private NewsContract.Presenter presenter;

    @Override
    public void loadClicked() {
//        presenter.loadNews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplicationComponentMVP
                .builder()
                .newsPresenterModule(new NewsPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        presenter.onViewAttached();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
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
