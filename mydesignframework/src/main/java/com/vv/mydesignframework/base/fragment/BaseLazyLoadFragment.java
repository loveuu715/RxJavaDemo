package com.vv.mydesignframework.base.fragment;

import android.os.Bundle;
import android.view.View;

/**
 * Created by VV on 2016/8/24.
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {

    private boolean isFirstLoad = true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initInjector();
        initView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        }
    }

    private void onVisible() {
        if (isFirstLoad && isPrepare) {
            initData();
            isFirstLoad = false;
        }
    }
}
