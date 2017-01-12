package com.nik.noveo.moxy.base.di;

import dagger.MembersInjector;

public interface ComponentBuilder<C extends MembersInjector> {
    C build();
}