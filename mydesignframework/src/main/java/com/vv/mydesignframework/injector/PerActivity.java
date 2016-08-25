package com.vv.mydesignframework.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by VV on 2016/8/24.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
