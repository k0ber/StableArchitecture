package com.nik.noveo.mvp_factory.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.mvp_factory.R;
import com.nik.noveo.mvp_factory.base.BaseActivity;
import com.nik.noveo.mvp_factory.base.PresenterFactory;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsActivity extends BaseActivity<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected PresenterFactory<NewsPresenter> getPresenterFactory() {
        return () -> new NewsPresenter(new NewsRepository());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNewsText(String text) {
        newsText.setText(text);
    }

    @OnClick(R.id.fab_load)
    void onLoadNewsClicked() {
        presenter.loadNews();
    }
}
