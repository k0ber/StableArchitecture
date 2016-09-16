package com.nik.noveo.mvp_factory.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private boolean willBeRecreated;


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract PresenterFactory<P> getPresenterFactory();
    protected P presenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        Class pClass = ((Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        presenter = (P) PresenterCache.get(pClass, getPresenterFactory());
        presenter.attachView(this);
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
        PresenterCache.remove(presenter.getClass());
    }
}