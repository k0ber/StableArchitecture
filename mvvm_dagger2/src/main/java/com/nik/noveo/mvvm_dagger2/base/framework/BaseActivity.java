package com.nik.noveo.mvvm_dagger2.base.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.nik.noveo.mvvm_dagger2.base.di.DiManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity<VM extends ViewModel> extends AppCompatActivity {

    private boolean willBeRecreated;
    protected CompositeSubscription subscriptions;
    @Inject protected VM viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        subscriptions = new CompositeSubscription();
        ButterKnife.bind(this);

        getDiManager().makeInjection(this);
    }

    @LayoutRes protected abstract int getLayoutId();

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
     * calls when activity are destroying without recreation
     */
    protected void onFinish() {
        viewModel.release();
        getDiManager().release(this);
    }

    private DiManager getDiManager() {
        return ((App) getApplication()).getDiManager();
    }
}