package com.nik.noveo.mvp_dagger2.di;

import com.nik.noveo.mvp_dagger2.news.NewsContract;
import com.nik.noveo.mvp_dagger2.news.NewsPresenter;
import com.nik.noveo.mvp_dagger2.news.NewsRepository;

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
