package com.vv.mydesignframework;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.vv.mydesignframework.common.exception.LocalExceptionHelper;
import com.vv.mydesignframework.injector.component.ApplicationComponent;
import com.vv.mydesignframework.injector.component.DaggerApplicationComponent;
import com.vv.mydesignframework.injector.module.ApplicationModul;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by VV on 2016/8/22.
 */
public class BaseApplication extends Application {

    private static BaseApplication sBaseApplication;

    private ApplicationComponent mApplicationComponent;

    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        //分包
        MultiDex.install(this);
        super.onCreate();
        //内存泄露检测
        LeakCanary.install(this);
        init();
    }

    private void init() {
        sBaseApplication = this;
        initAppComponent();
        initException();
    }

    public static BaseApplication getApplication() {
        return sBaseApplication;
    }

    private void initAppComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().
                applicationModul(new ApplicationModul(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initException() {
        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalExceptionHelper(this));
    }


    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
}
