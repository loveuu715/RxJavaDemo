package com.vv.mydesignframework.injector.component;

import android.app.Activity;

import com.vv.mydesignframework.injector.PerActivity;
import com.vv.mydesignframework.injector.module.ActivityModule;

import dagger.Component;

/**
 * Created by VV on 2016/8/24.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
