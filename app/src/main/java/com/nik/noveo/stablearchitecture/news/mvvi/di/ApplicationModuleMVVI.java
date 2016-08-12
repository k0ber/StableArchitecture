package com.nik.noveo.stablearchitecture.news.mvvi.di;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.mvvi.NewsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModuleMVVI {

    @Provides
    public NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }



    @Provides
    public NewsViewModel provideNewsViewModel(NewsRepository newsRepository) {
        return new NewsViewModel(newsRepository);
    }

}
