package com.vv.mydesignframework.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by VV on 2016/8/24.
 */
public class HeaderInterceptor implements Interceptor {

    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";
    private static final String FROM_ANDROID = "ayb-android";

    public HeaderInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader(HEADER_X_HB_Client_Type, FROM_ANDROID)
                .build();
        return chain.proceed(request);
    }
}
