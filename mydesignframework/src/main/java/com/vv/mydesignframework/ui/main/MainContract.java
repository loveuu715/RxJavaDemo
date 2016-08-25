package com.vv.mydesignframework.ui.main;

import com.vv.mydesignframework.base.mvp.BasePresenter;
import com.vv.mydesignframework.base.mvp.BaseView;

/**
 * Created by VV on 2016/8/24.
 */
public interface MainContract {

    interface View extends BaseView {
        void showSuccess(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onBtnClick();
    }
}
