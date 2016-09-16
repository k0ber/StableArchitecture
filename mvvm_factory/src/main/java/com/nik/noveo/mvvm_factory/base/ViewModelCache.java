package com.nik.noveo.mvvm_factory.base;

import android.support.v4.util.SimpleArrayMap;

class ViewModelCache {

    private static SimpleArrayMap<Class, ViewModel> viewModels;

    static ViewModel get(Class viewModelClass, ViewModelFactory viewModelFactory) {
        if (viewModels == null) {
            viewModels = new SimpleArrayMap<>();
        }

        ViewModel viewModel = viewModels.get(viewModelClass);
        if (viewModel == null) {
            viewModel = viewModelFactory.createViewModel();
            viewModels.put(viewModelClass, viewModel);
        }
        return viewModel;
    }

    static void remove(Class viewModelClass) {
        if (viewModels != null) {
            ViewModel removedViewModel = viewModels.remove(viewModelClass);
            if (removedViewModel != null) {
                removedViewModel.onViewDied();
            }
        }
    }
}
