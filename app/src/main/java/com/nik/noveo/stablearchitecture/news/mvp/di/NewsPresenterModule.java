package com.nik.noveo.stablearchitecture.news.mvp.di;


import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.mvp.NewsContract;
import com.nik.noveo.stablearchitecture.news.mvp.NewsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPresenterModule {

    private final NewsContract.View view;

    public NewsPresenterModule(NewsContract.View view) {
        this.view = view;
    }

    @Provides
    NewsContract.View provideNewsContractView() {
        return view;
    }

    @Provides
    NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    NewsPresenter providePresenter(NewsRepository newsRepository, NewsContract.View view) {
        return new NewsPresenter(newsRepository, view);
    }
}
