package com.nik.noveo.stablearchitecture.di;


import com.nik.noveo.stablearchitecture.news.NewsContract;
import com.nik.noveo.stablearchitecture.news.NewsPresenter;
import com.nik.noveo.stablearchitecture.news.NewsRepository;

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
    NewsContract.Presenter providePresenter(NewsRepository newsRepository, NewsContract.View view) {
        return new NewsPresenter(newsRepository, view);
    }
}
