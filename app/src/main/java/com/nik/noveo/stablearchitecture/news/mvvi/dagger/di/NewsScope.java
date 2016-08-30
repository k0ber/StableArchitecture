package com.nik.noveo.stablearchitecture.news.mvvi.dagger.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface NewsScope {
}