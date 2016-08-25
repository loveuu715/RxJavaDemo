package com.vv.mydesignframework.injector.module;

import android.content.Context;

import com.vv.mydesignframework.api.DemoApi;
import com.vv.mydesignframework.base.fragment.demo.DemoBean;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public DemoApi provideDemoApi(DemoBean demoBean, @Named("api")OkHttpClient okHttpClient, Context context) {
        return  new DemoApi(demoBean, okHttpClient, context);
    }
}
