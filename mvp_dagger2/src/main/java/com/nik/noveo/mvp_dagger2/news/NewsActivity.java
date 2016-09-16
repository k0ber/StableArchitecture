package com.nik.noveo.mvp_dagger2.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.nik.noveo.mvp_dagger2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

public abstract class NewsActivity extends AppCompatActivity {

    @BindView(R.id.tv_news) public TextView newsText;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.toolbar) public Toolbar toolbar;

    private boolean willBeRecreated;
    protected CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onStart() {
        willBeRecreated = false;
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
        if (!willBeRecreated) {
            onFinish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    /**
     * will be called when activity destroyed without recreation
     */
    protected void onFinish() {
    }

    public void setNewsText(String text) {
        newsText.setText(text);
    }

    public void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @OnClick(R.id.fab_load)
    abstract public void loadClicked();
}
