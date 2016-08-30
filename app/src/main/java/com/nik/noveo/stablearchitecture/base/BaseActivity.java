package com.nik.noveo.stablearchitecture.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean willBeRecreated;
    protected CompositeSubscription subscriptions;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract PresenterFactory getPresenterFactory();

    protected abstract void onPresenterCreated(BasePresenter presenter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        subscriptions = new CompositeSubscription();
        ButterKnife.bind(this);

        onPresenterCreated(PresenterCache.get(getPresenterFactory()));
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

    /**
     * will be called when activity destroyed without recreation
     */
    protected void onFinish() {
        PresenterCache.remove(getPresenterFactory().getPresenterClass());
    }
}