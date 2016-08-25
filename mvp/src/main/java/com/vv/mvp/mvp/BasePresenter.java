package com.vv.mvp.mvp;

/**
 * Created by VV on 2016/8/21.
 */
public abstract class BasePresenter<T> {
    public BaseModel MODEL = new DefaultModel();
    public BaseView VIEW = null;

    public BasePresenter(BaseView<T> view) {
        VIEW = view;
    }

    abstract void fetch();
}
