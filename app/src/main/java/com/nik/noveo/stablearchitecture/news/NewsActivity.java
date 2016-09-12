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


public class NewsActivity extends BaseActivity implements NewsContract.View {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    private NewsContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected PresenterFactory getPresenterFactory() {
        return new PresenterFactory() {
            @Override
            public BasePresenter createPresenter() {
                return new NewsPresenter(new NewsRepository());
            }

            @Override
            public Class getPresenterClass() {
                return NewsPresenter.class;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (NewsContract.Presenter) presenter;
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
