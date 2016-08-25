package com.vv.mydesignframework.injector.module;

import android.app.Service;

import com.vv.mydesignframework.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class ServiceModule {

    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    public Service provideService() {
        return mService;
    }

}
