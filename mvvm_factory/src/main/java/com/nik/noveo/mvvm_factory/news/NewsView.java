package com.nik.noveo.mvvm_factory.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.mvvm_factory.R;
import com.nik.noveo.mvvm_factory.base.BaseActivity;
import com.nik.noveo.mvvm_factory.base.ViewModelFactory;

import butterknife.BindView;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;


public class NewsView extends BaseActivity<NewsViewModel> {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    private CompositeSubscription viewStateSubscriptions;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected ViewModelFactory<NewsViewModel> getViewModelFactory() {
        return () -> new NewsViewModel(new NewsRepository());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        viewStateSubscriptions = new CompositeSubscription();
        initBindings();
    }

    @Override
    protected void onDestroy() {
        viewStateSubscriptions.clear();
        super.onDestroy();
    }

    private void initBindings() {
        viewStateSubscriptions.addAll(
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
