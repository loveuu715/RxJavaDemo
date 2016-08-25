package com.vv.mydesignframework.base.mvp;

/**
 * Created by VV on 2016/8/22.
 */

import android.support.annotation.NonNull;

public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T view);

    void detachView();

//    /**
//     * 需要持有view和model的引用
//     */
//    protected BaseView VIEW;
//
//    //默认,model
//    protected BaseModel MODEl = new DefaultModel();
//
//    public BasePresenter(BaseView baseView) {
//        VIEW = baseView;
//    }
//
//    /**
//     * 初始化
//     */
//    public abstract void setup();
//
//    /**
//     * default Model
//     */
//    public static class DefaultModel implements BaseModel<String> {
//
//        @Override
//        public void loadData(final ModelCallback<String> modelCallback) {
//            modelCallback.onComplete("这是默认的model");
//        }
//
//        @Override
//        public void refreshData(final ModelCallback<String> modelCallback) {
//            //加载数据的动作
//            modelCallback.onComplete("这是默认的mdoel");
//        }
//
//        @Override
//        public void stopLoad() {
//        }
//
//        @Override
//        public void loadEmpty() {
//        }
//    }
}
