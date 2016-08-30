package com.nik.noveo.stablearchitecture.news.mvvi.dagger.di;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.NewsVM;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsVMModule {

    @Provides
    @NewsScope
    NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    @NewsScope
    NewsVM provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsVM(newsRepository);
    }
}