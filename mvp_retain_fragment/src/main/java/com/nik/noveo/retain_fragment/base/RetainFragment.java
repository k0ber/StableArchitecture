package com.nik.noveo.retain_fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import rx.functions.Func0;


public class RetainFragment<O> extends Fragment {

    private O storedObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressWarnings("unchecked")
    public static <P> RetainFragment<P> getOrCreate(FragmentManager fm, String tag, Func0<P> func) {
        RetainFragment<P> retainFragment = (RetainFragment<P>) fm.findFragmentByTag(tag);

        if (retainFragment == null) {
            retainFragment = new RetainFragment<>();
            retainFragment.storedObject = func.call();
            fm.beginTransaction().add(retainFragment, tag)
                    .commitAllowingStateLoss();

        }

        return retainFragment;
    }

    O getStoredObject() {
        return storedObject;
    }

    public static void remove(FragmentManager fm, String tag) {
        if (!fm.isDestroyed()) {
            Fragment fragment = fm.findFragmentByTag(tag);
            if (fragment != null) {
                fm.beginTransaction().remove(fragment)
                        .commitAllowingStateLoss();
            }
        }
    }


}
