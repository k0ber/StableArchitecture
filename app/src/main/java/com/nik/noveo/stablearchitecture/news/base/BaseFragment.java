package com.nik.noveo.stablearchitecture.news.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


public abstract class BaseFragment extends Fragment {

    private boolean willBeRecreated;
    private BasePresenter presenter;

    abstract Class<? extends BasePresenter> getPresenterClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PresenterCache.get(getPresenterClass());
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
    public void onDestroyView() {
        if (!willBeRecreated) {
            PresenterCache.remove(presenter.getClass());
        }
        super.onDestroyView();
    }
}
