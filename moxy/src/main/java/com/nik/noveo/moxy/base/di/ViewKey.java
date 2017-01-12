package com.nik.noveo.moxy.base.di;

import dagger.MapKey;

@MapKey
@interface ViewKey {
    Class value();
}
