package com.nik.noveo.mvp_dagger2.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected CompositeSubscription subscriptions;

    @Inject protected P presenter;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void makeInject();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        subscriptions = new CompositeSubscription();
        ButterKnife.bind(this);
        makeInject();
        if (presenter == null) {
            throw new RuntimeException("Presenter must be injected");
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        subscriptions.unsubscribe();
        if (!isChangingConfigurations()) {
            onFinish();
        }
        super.onDestroy();
    }

    protected void onFinish() {
        presenter.onViewDied();
    }
}