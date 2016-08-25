package com.vv.mydesignframework.injector.component;

import android.content.Context;

import com.vv.mydesignframework.BaseApplication;
import com.vv.mydesignframework.base.BaseActivity;
import com.vv.mydesignframework.injector.module.ApiModule;
import com.vv.mydesignframework.injector.module.ApplicationModul;
import com.vv.mydesignframework.injector.module.DBModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 注解器,其生命周与Application一致
 * Created by VV on 2016/8/24.
 */
@Singleton
@Component(modules = {ApplicationModul.class, ApiModule.class, DBModule.class})
public interface ApplicationComponent {

    Context getContext();

    void inject(BaseApplication mApplication);
    void inject(BaseActivity mBaseActivity);
}
