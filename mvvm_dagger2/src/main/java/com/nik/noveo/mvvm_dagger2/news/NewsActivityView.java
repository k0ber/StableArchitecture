package com.nik.noveo.mvvm_dagger2.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.nik.noveo.mvvm_dagger2.App;
import com.nik.noveo.mvvm_dagger2.base.BaseActivity;
import com.nik.noveo.mvvm_dagger2.di.NewsComponent;
import com.nik.noveo.mvvm_dagger2.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivityView extends BaseActivity<NewsComponent, NewsViewModel> {

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.tv_news) TextView newsText;
    @BindView(R.id.toolbar) public Toolbar toolbar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onFinish() {
//        newsViewModel.release(); // todo ?!
//        App.get().releaseNewsComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

//        NewsComponent component = App.get().getNewsComponent();
//        if (component == null) {
//            component = App.get().createNewsComponent();
//        }
//        component.inject(this);

        initBindings();
    }

    private void initBindings() {
        subscriptions.addAll(
                viewModel.postsObservable().subscribe(this::setNewsText),
                viewModel.loadingObservable().subscribe(this::setLoading)
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
        viewModel.loadNews();
    }
}
