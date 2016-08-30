package com.nik.noveo.stablearchitecture.news.mvvi.dagger;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nik.noveo.stablearchitecture.App;
import com.nik.noveo.stablearchitecture.R;
import com.nik.noveo.stablearchitecture.news.mvvi.dagger.di.NewsVMComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class BaseActivityView extends BaseActivity {

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.tv_news) TextView newsText;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    @Inject NewsVM newsVM;


    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onFinish() {
        newsVM.release();
        App.get().releaseNewsVMComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        NewsVMComponent component = App.get().getNewsVMComponent();
        if (component == null) {
            component = App.get().createNewsVMComponent();
            Log.w("NIX", "new component created");
        }
        component.inject(this);
        Log.w("NIX", "news component: " + component.toString());
        Log.w("NIX", "injected vm: " + newsVM.toString());

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
