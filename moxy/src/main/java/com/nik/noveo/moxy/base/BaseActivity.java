package com.nik.noveo.moxy.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends MvpAppCompatActivity {

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