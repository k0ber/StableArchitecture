package com.nik.noveo.mvp_dagger2.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.mvp_dagger2.App;
import com.nik.noveo.mvp_dagger2.R;
import com.nik.noveo.mvp_dagger2.base.BaseActivity;
import com.nik.noveo.mvp_dagger2.di.NewsComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivityView extends BaseActivity<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    //region di logic should be separated from this class in future
    @Override
    protected void onFinish() {
        App.get().releaseNewsComponent();
    }

    @Override
    protected void makeInject() {
        NewsComponent component = App.get().getNewsComponent();
        if (component == null) {
            component = App.get().createNewsComponent();
        }
        component.inject(this);
    }
    //endregion

    @OnClick(R.id.fab_load)
    public void loadClicked() {
        presenter.loadNews();
    }

    @Override
    public void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNewsText(String text) {
        newsText.setText(text);
    }
}
