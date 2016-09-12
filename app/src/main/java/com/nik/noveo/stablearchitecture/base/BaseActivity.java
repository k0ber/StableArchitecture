package com.nik.noveo.stablearchitecture.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private boolean willBeRecreated;


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract PresenterFactory getPresenterFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        BasePresenter presenter = PresenterCache.get(getPresenterFactory());
        setPresenter(presenter);
        presenter.onViewReady(this);
    }

    @Override
    protected void onStart() {
        willBeRecreated = false;
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!willBeRecreated) {
            onFinish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    /**
     * will be called when activity destroyed without recreation
     */
    protected void onFinish() {
        PresenterCache.remove(getPresenterFactory().getPresenterClass());
    }
}