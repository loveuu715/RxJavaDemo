package com.vv.mvp.mvp;

/**
 * Created by VV on 2016/8/21.
 */
public interface BaseView<T> {
    void onLoading();
    void showData(T data);
}
