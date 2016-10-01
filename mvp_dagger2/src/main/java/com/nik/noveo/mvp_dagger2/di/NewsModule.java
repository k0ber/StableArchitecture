package com.nik.noveo.mvp_dagger2.di;


import com.nik.noveo.mvp_dagger2.news.NewsPresenter;
import com.nik.noveo.mvp_dagger2.news.NewsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    @NewsScope
    static NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    @NewsScope
    static NewsPresenter providePresenter(NewsRepository newsRepository) {
        return new NewsPresenter(newsRepository);
    }
}
