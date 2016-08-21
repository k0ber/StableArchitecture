package com.nik.noveo.stablearchitecture.news.mvvi.factory;

import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.base.BasePresenter;
import com.nik.noveo.stablearchitecture.news.base.PresenterFactory;

class NewsVMFactory implements PresenterFactory {

    private static class InstanceHolder {
        static final NewsVMFactory INSTANCE = new NewsVMFactory();
    }

    static NewsVMFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public BasePresenter createPresenter() {
        return new NewsVM(new NewsRepository());
    }

    @Override
    public Class getPresenterClass() {
        return NewsVM.class;
    }
}