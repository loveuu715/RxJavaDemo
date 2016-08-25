package com.vv.mydesignframework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.vv.mydesignframework.utils.LogUtil;

/**
 * Created by VV on 2016/8/24.
 */
public class AppService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("APP服务启动");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
