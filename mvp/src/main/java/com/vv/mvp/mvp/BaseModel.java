package com.vv.mvp.mvp;

/**
 * Created by VV on 2016/8/21.
 */
public interface BaseModel<T> {
    void loadData(ModelCallback<T> listener);
    interface ModelCallback<T>{
        void onComplete(T data);
    }
}
