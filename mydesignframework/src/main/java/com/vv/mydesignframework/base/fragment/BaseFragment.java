package com.vv.mydesignframework.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vv.mydesignframework.base.listener.FListener;
import com.vv.mydesignframework.injector.HasComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by VV on 2016/8/23.
 */
public abstract class BaseFragment extends PFragment {

    protected View mView;
    protected Context mContext;
    private Unbinder mUnbinder;
    protected FListener mFListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initInjector();
        mContext = getContext();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(bindLayout(), null);
            mUnbinder = ButterKnife.bind(this, mView);
            initView(mView);
            initEvent();
        }
        return mView;
    }

    /**
     * 依赖注入事件监听器
     *
     * @param listener
     */
    public void setFListener(FListener listener) {
        this.mFListener = listener;
    }

    protected abstract int bindLayout();

    public abstract void initInjector();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initEvent();


    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }
}
