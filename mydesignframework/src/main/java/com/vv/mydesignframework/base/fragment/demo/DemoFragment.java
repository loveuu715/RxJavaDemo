package com.vv.mydesignframework.base.fragment.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.vv.mydesignframework.base.fragment.BaseRefreshFragment;

import org.byteam.superadapter.recycler.OnItemClickListener;
import org.byteam.superadapter.recycler.SuperAdapter;

/**
 * Created by VV on 2016/8/24.
 */
public class DemoFragment extends BaseRefreshFragment {

    @Override
    protected void refreshData(SwipeRefreshLayout refreshLayout) {

    }

    @Override
    protected void loadData(XRecyclerView recycleView) {

    }

    @Override
    protected SuperAdapter getAdapter() {
        //TODO 替换成自己的Adapter,需要继承SuperAdapter<bean>
        DemoAdapter adapter = new DemoAdapter(getContext(), null, 0);
        //TODO 提前设置item的点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });
        return adapter;
    }

    @Override
    public void initInjector() {

    }

    @Override
    protected void initData() {

    }
}
