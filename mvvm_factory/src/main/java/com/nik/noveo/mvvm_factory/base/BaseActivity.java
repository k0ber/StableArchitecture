package com.nik.noveo.mvvm_factory.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;


public abstract class BaseActivity<VM extends ViewModel> extends AppCompatActivity {

    protected VM viewModel;


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract ViewModelFactory<VM> getViewModelFactory();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        Class vmClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        viewModel = (VM) ViewModelCache.get(vmClass, getViewModelFactory());
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            onFinish();
        }
        super.onDestroy();
    }

    /**
     * will be called when activity destroyed without recreation
     */
    protected void onFinish() {
        ViewModelCache.remove(viewModel.getClass());
    }
}