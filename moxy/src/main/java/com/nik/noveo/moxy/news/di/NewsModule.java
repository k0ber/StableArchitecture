package com.nik.noveo.moxy.news.di;


import com.nik.noveo.moxy.news.NewsPresenter;
import com.nik.noveo.moxy.news.NewsRepository;

import dagger.Module;
import dagger.Provides;

@Module
class NewsModule {

    @Provides
    static NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    static NewsPresenter provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsPresenter(newsRepository);
    }
}