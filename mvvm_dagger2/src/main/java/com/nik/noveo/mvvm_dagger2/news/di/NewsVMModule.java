package com.nik.noveo.mvvm_dagger2.news.di;


import com.nik.noveo.mvvm_dagger2.news.NewsRepository;
import com.nik.noveo.mvvm_dagger2.news.NewsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
class NewsVMModule {

    @Provides
    @NewsScope
    static NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    @NewsScope
    static NewsViewModel provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsViewModel(newsRepository);
    }
}