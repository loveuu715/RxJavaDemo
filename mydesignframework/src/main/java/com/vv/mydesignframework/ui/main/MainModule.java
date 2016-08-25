package com.vv.mydesignframework.ui.main;

import com.vv.mydesignframework.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class MainModule {
    private MainActivity mMainActivity;

    public MainModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }


    @Provides
    @PerActivity
    public String provideString(){
        return "mainmain";
    }

}
