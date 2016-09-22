package com.nik.noveo.retain_fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Func0;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private static final String RETAIN_FRAGMENT_TAG = BaseFragment.class.getCanonicalName();
    private Unbinder unbinder;
    private boolean willBeRecreated;
    protected P presenter;


    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        RetainFragment<P> retainFragment = RetainFragment.getOrCreate(getFragmentManager(),
                RETAIN_FRAGMENT_TAG, getCreatePresenterFunc());
        presenter = retainFragment.getStoredObject();
        presenter.attachView(this);
    }

    protected abstract Func0<P> getCreatePresenterFunc();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        willBeRecreated = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!willBeRecreated) {
            onFinish();
        }
    }

    protected void onFinish() {
        presenter.onViewDied();
        RetainFragment.remove(getFragmentManager(), RETAIN_FRAGMENT_TAG);
    }
}
