package com.vv.mydesignframework.ui.main;

import android.widget.TextView;

import com.vv.mydesignframework.R;
import com.vv.mydesignframework.base.BaseActivity;
import com.vv.mydesignframework.injector.HasComponent;
import com.vv.mydesignframework.utils.LogUtil;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View, HasComponent<MainComponent>{

    private MainComponent mMainComponent;
    @Inject
    MainPresenter mMainPresenter;

    private TextView tv;


    @Override
    protected void initInjector() {
        mMainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule(this))
                .build();
        mMainComponent.inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void showSuccess(String msg) {
        LogUtil.i("hate", msg);
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }
}


