package com.nik.noveo.stablearchitecture.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.stablearchitecture.R;
import com.nik.noveo.stablearchitecture.base.BaseActivity;
import com.nik.noveo.stablearchitecture.base.BasePresenter;
import com.nik.noveo.stablearchitecture.base.PresenterFactory;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsView extends BaseActivity {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    private NewsViewModel newsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected PresenterFactory getPresenterFactory() {
        return new PresenterFactory() {
            @Override
            public BasePresenter createPresenter() {
                return new NewsViewModel(new NewsRepository());
            }

            @Override
            public Class getPresenterClass() {
                return NewsViewModel.class;
            }
        };
    }

    @Override
    protected void onPresenterCreated(BasePresenter presenter) {
        newsViewModel = (NewsViewModel) presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        initBindings();
    }

    private void initBindings() {
        subscriptions.addAll(
                newsViewModel.postsObservable().subscribe(this::setNewsText),
                newsViewModel.loadingObservable().subscribe(this::setLoading)
        );
    }

    private void setNewsText(String text) {
        newsText.setText(text);
    }

    private void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.fab_load)
    public void loadClicked() {
        newsViewModel.loadNews();
    }
}
