package com.vv.mydesignframework.base.mvp;

/**
 * Created by VV on 2016/8/22.
 */
public interface BaseModel<T> {
    /**
     * 加载数据
     *
     * @param modelCallback
     */
    void loadData(final ModelCallback<T> modelCallback);

    /**
     * 刷新数据
     *
     * @param modelCallback
     */
    void refreshData(final ModelCallback<T> modelCallback);

    /**
     * 停止加载
     */
    void stopLoad();

    void loadEmpty();

    /**
     * Model的统一回调
     *
     * @param <T>
     */
    public interface ModelCallback<T> {
        void onComplete(final T result);

        void onError(final int errCode, final String errMsg, final Throwable throwable);
    }
}
