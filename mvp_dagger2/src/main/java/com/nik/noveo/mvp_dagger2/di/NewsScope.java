package com.nik.noveo.mvp_dagger2.di;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface NewsScope {
}
