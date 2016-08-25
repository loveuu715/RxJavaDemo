package com.vv.mydesignframework.injector.module;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.vv.mydesignframework.BaseApplication;
import com.vv.mydesignframework.api.HeaderInterceptor;
import com.vv.mydesignframework.api.LogInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class ApplicationModul {
    /**
     * 服务器地址
     */
    // 请求公共部分
    private static final String BASE_URL = "https://github.com/";


    private final Context context;


    public ApplicationModul(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Looper provideMainLooper(Application application) {
        return application.getMainLooper();
    }

    @Provides
    @Singleton
    public Handler provideHandler() {
        return new Handler();
    }

    @Provides
    @Singleton
    public int provideMainThreadId() {
        return android.os.Process.myTid();
    }

    @Provides
    @Singleton
    public Thread provideMainThread() {
        return Thread.currentThread();

    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    @Named("api")
    OkHttpClient provideApiOkHttpClient() {
        //设置 请求的缓存
        File cacheFile = new File(BaseApplication.getApplication().getCacheDir(), "netCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//超时时间15秒
                .addInterceptor(new LogInterceptor())//设置请求日志拦截器,release取消掉
                .addInterceptor(new HeaderInterceptor())//所有请求头拦截器
                .cache(cache);//设置请求缓存
        return builder.build();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient mOkHttpClient) {
        OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        builder.interceptors().clear();
        return builder.build();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(Context mContext) {
        return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /*@Provides
    @Singleton
    CookieInterceptor provideCookieInterceptor(UserStorage mUserStorage) {
        return new CookieInterceptor(mUserStorage);
    }*/

   /* @Provides
    @Singleton
    UserStorage provideUserStorage(Context mContext) {
        return new UserStorage(mContext);
    }*/

    /*@Provides
    @Singleton
    RequestHelper provideRequestHelper(Context mContext,
                                       UserStorage mUserStorage) {
        return new RequestHelper(mContext, mUserStorage);
    }*/

   /* @Provides
    @Singleton
    OkHttpHelper provideOkHttpHelper(OkHttpClient mOkHttpClient) {
        return new OkHttpHelper(mOkHttpClient);
    }*/
}
