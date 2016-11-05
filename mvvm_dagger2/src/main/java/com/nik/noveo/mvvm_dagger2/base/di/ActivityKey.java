package com.nik.noveo.mvvm_dagger2.base.di;

import dagger.MapKey;

@MapKey
@interface ActivityKey {
    Class value();
}
