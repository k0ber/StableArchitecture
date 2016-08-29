package com.nik.noveo.stablearchitecture.news.mvvi.factory;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.stablearchitecture.R;
import com.nik.noveo.stablearchitecture.news.NewsRepository;
import com.nik.noveo.stablearchitecture.news.base.BasePresenter;
import com.nik.noveo.stablearchitecture.news.base.PresenterFactory;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsView extends BaseActivity {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    private NewsVM newsVM;

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    PresenterFactory getPresenterFactory() {
        return new PresenterFactory() {
            @Override
            public BasePresenter createPresenter() {
                return new NewsVM(new NewsRepository());
            }

            @Override
            public Class getPresenterClass() {
                return NewsVM.class;
            }
        };
    }

    @Override
    void onPresenterCreated(BasePresenter presenter) {
        newsVM = (NewsVM) presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        initBindings();
    }

    private void initBindings() {
        subscriptions.addAll(
                newsVM.postsObservable().subscribe(this::setNewsText),
                newsVM.loadingObservable().subscribe(this::setLoading)
        );
    }

    public void setNewsText(String text) {
        newsText.setText(text);
    }

    public void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.fab_load)
    public void loadClicked() {
        newsVM.loadNews();
    }
}
