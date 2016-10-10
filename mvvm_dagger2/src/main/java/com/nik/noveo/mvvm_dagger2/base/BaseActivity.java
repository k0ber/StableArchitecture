package com.nik.noveo.mvvm_dagger2.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.nik.noveo.mvvm_dagger2.ComponentInjector;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity<CI extends ComponentInjector, VM extends ViewModel> extends AppCompatActivity {

    private boolean willBeRecreated;
    protected CompositeSubscription subscriptions;

    @Inject protected VM viewModel;


    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        subscriptions = new CompositeSubscription();
        ButterKnife.bind(this);

        Class injectorClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        DiManager.getInjector(injectorClass).inject(this);
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
    protected abstract void onFinish();
}