package com.vv.mydesignframework.injector.component;

import android.app.Service;

import com.vv.mydesignframework.injector.PerService;
import com.vv.mydesignframework.injector.module.ServiceModule;

import dagger.Component;

/**
 * 生命周期和Service一致
 * Created by VV on 2016/8/24.
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


    Service getServiceContext();

    //TODO 注解自己的Service

}
