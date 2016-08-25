package com.vv.vv.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {


    private Observer mSubscriber;
    private Observer<String> mObserver;
    private Observable mObservable;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                steupRxJava();
                requestNet();
            }
        });
    }

    private void requestNet() {
        try {
            URL url = new URL("http://192.168.31.164:8080/webDemo/hello");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200)
                Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void steupRxJava() {

        mSubscriber = new Subscriber() {


            @Override
            public void onStart() {
                Log.i("hate", "完成");
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }

        };

        //创建观察者
        mObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("hate", "完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("hate", s);
            }
        };

        //创建被观察者
        mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onNext("ok");
                subscriber.onCompleted();
            }
        });

        //关联
        mSubscription = mObservable.subscribe(mSubscriber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            //取消订阅
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();

            }
        }
    }
}
