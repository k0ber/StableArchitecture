package com.nik.noveo.stablearchitecture.di;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.NewsViewModel;

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
    NewsViewModel provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsViewModel(newsRepository);
    }
}