package com.vv.mydesignframework.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.vv.mydesignframework.BaseApplication;
import com.vv.mydesignframework.base.event.EventObject;
import com.vv.mydesignframework.injector.component.ActivityComponent;
import com.vv.mydesignframework.injector.component.ApplicationComponent;
import com.vv.mydesignframework.injector.module.ActivityModule;
import com.vv.mydesignframework.utils.CheckNotNull;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 基类功能待完善
 * Created by VV on 2016/8/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    protected Context mContext;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        //不能竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setContentView(bindLayout());
        initView();
        initInjector();
        initEvent();
    }

    protected abstract void initInjector();

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public abstract int bindLayout();

    public abstract void initView();

    public abstract void initEvent();

    @Subscribe
    public void onEvent(EventObject obj) {}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO 自定义界面关闭动画
    }

    public void setFullScreen() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void quitFullScreen() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(params);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 将Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        CheckNotNull.check(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
