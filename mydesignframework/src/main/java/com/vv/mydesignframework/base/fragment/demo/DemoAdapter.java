package com.vv.mydesignframework.base.fragment.demo;

import android.content.Context;

import org.byteam.superadapter.recycler.BaseViewHolder;
import org.byteam.superadapter.recycler.IMultiItemViewType;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.List;

/**
 * Created by VV on 2016/8/24.
 */
public class DemoAdapter extends SuperAdapter<DemoBean> {

    public DemoAdapter(Context context, List<DemoBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    public DemoAdapter(Context context, List<DemoBean> items, IMultiItemViewType<DemoBean> multiItemViewType) {
        super(context, items, multiItemViewType);
    }

    @Override
    public void onBind(int viewType, BaseViewHolder holder, int position, DemoBean item) {
        //TODO 对item的操作
    }
}
