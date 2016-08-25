package com.vv.mydesignframework.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vv.mydesignframework.api.Api;
import com.vv.mydesignframework.injector.PerActivity;

import javax.inject.Inject;

/**
 * Created by VV on 2016/8/24.
 */
@PerActivity
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private Context mContext;

    private MainBean mBean;

    @Inject
    public MainPresenter(Context context, MainBean bean) {
        this.mContext = context;
        this.mBean = bean;
    }

    @Override
    public void onBtnClick() {

    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mView = view;
        init();
    }

    private void init() {
        mView.showSuccess(mBean.getDesc());
    }

    @Override
    public void detachView() {

    }
}
