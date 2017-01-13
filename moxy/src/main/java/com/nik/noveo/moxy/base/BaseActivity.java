package com.nik.noveo.moxy.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends MvpAppCompatActivity {

    @Inject
    Provider<P> presenterProvider;

    protected P createPresenter() {
        return presenterProvider.get();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App) getApplication()).inject(this);

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

}