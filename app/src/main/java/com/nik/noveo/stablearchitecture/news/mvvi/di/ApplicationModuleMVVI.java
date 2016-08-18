package com.nik.noveo.stablearchitecture.news.mvvi.di;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.mvvi.NewsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModuleMVVI {

    @Provides
    NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    NewsViewModel provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsViewModel(newsRepository);
    }
}
