package com.nik.noveo.stablearchitecture.news.mvvi.dagger;

import com.nik.noveo.stablearchitecture.news.NewsRepository;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModuleMVVI {

    @Provides
    NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    NewsVM provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsVM(newsRepository);
    }
}