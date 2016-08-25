package com.vv.mydesignframework.api;

import android.content.Context;

import com.vv.mydesignframework.base.fragment.demo.DemoBean;

import okhttp3.OkHttpClient;

/**
 * Created by VV on 2016/8/24.
 */
public class DemoApi {
    private DemoBean mDemoBean;
    private OkHttpClient mOkHttpClient;
    private Context mContext;

    public DemoApi(DemoBean demoBean, OkHttpClient okHttpClient, Context context) {
        mDemoBean = demoBean;
        mOkHttpClient = okHttpClient;
        mContext = context;
    }
}
