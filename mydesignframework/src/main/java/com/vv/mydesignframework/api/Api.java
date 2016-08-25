package com.vv.mydesignframework.api;

import com.vv.mydesignframework.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by VV on 2016/8/24.
 */
public class Api {
    /**
     * 服务器地址
     */
    // 请求公共部分
    private static final String BASE_URL = "http://www.baidu.com/";

    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";
    private static final String FROM_ANDROID = "ayb-android";

    private static ApiService service;
    private static Retrofit retrofit;

    public static ApiService getService() {
        if (service == null) {
            service = getRetrofit().create(ApiService.class);
        }
        return service;
    }

    /**
     * 拦截器  给所有的请求添加消息头
     */
    private static Interceptor sHeaderInterceptor = new Interceptor(){
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(HEADER_X_HB_Client_Type, FROM_ANDROID)
                    .build();
            return chain.proceed(request);
        }
    };
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            // log拦截器  打印所有的log
            LogInterceptor logInterceptor = new LogInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 请求的缓存
            File cacheFile = new File(BaseApplication.getApplication().getCacheDir(), "netCache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)//超时时间15秒
                    .addInterceptor(logInterceptor)//设置请求日志拦截器,release取消掉
                    .addInterceptor(sHeaderInterceptor)//所有请求头拦截器
                    .cache(cache)//设置请求缓存
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())//使用Gson装换工厂类
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//回调使用RxJava工厂类
                    .build();
        }
        return retrofit;
    }

    /**
     * 对 Observable<T> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T > Observable<T> applySchedulers(Observable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T tResponse) {
                        return flatResponse(tResponse);
                    }
                });
    }

    /**
     * 对网络接口返回的Response进行分割操作 对于json 解析错误以及返回的 响应实体为空的情况
     * @param response
     * @return
     */
    public < T > Observable<T> flatResponse(final T response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response != null) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response);
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException("自定义异常类型", "解析json错误或者服务器返回空的json"));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }
        });
    }

    /**
     *
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }
}
