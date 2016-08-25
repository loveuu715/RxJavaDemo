package com.vv.mydesignframework.ui.main;

import com.vv.mydesignframework.injector.PerActivity;
import com.vv.mydesignframework.injector.component.ApplicationComponent;
import com.vv.mydesignframework.injector.module.ActivityModule;

import dagger.Component;

/**
 * Created by VV on 2016/8/24.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
