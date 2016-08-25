package com.vv.mydesignframework.injector.module;

import android.app.Activity;

import com.vv.mydesignframework.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
