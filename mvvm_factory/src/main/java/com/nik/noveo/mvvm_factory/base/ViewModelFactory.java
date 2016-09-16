package com.nik.noveo.mvvm_factory.base;

public interface ViewModelFactory<P extends ViewModel> {
    P createViewModel();
}