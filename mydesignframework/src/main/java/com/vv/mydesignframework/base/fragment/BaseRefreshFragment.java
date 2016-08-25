package com.vv.mydesignframework.base.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.vv.mydesignframework.R;
import com.vv.mydesignframework.base.itemspace.SpacesItemDecoration;
import com.vv.mydesignframework.utils.UIUtils;

import org.byteam.superadapter.recycler.SuperAdapter;

import butterknife.BindView;

/**
 * 自带RecyclerView的Fragment
 * Created by VV on 2016/8/23.
 */
public abstract class BaseRefreshFragment extends BaseFragment {

    @BindView(R.id.recycle_view_base_fragment)
    protected XRecyclerView recycleView;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    private SpacesItemDecoration itemDecoration;
    protected int pageNum = 1;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_base_refresh;
    }

    @Override
    protected void initView(View view) {
        itemDecoration = new SpacesItemDecoration(UIUtils.dip2px(mContext, 10));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, android.R.color.holo_green_light,
                android.R.color.holo_blue_light, android.R.color.holo_red_light);
        final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout != null) {
                    refreshData(swipeRefreshLayout);
                }
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefreshListener.onRefresh(); // 第一次必须手动调用，直接调用setRefreshing不会触发onRefresh方法
            }
        }, 50);

        recycleView.setPullRefreshEnabled(false);
        recycleView.setLoadingMoreEnabled(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.addItemDecoration(itemDecoration);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                if (recycleView != null) {
                    loadData(recycleView);
                }
            }
        });
        SuperAdapter adapter = getAdapter();
        if (adapter != null) {
            recycleView.setAdapter(adapter);
        }
        View headView = getHeaderView();
        if (headView != null) {
            recycleView.addHeaderView(headView);
        }
    }

    @Override
    protected void initEvent() {}

    public void setPadding(int left, int top, int right, int bottom) {
        recycleView.setPadding(left, top, right, bottom);
    }

    public void setItemDecoration(int space) {
        recycleView.removeItemDecoration(itemDecoration);
        itemDecoration = new SpacesItemDecoration(space);
        recycleView.addItemDecoration(itemDecoration);
    }

    protected abstract void refreshData(SwipeRefreshLayout refreshLayout);

    protected abstract void loadData(XRecyclerView recycleView);

    protected abstract SuperAdapter getAdapter();

    protected void stopRefresh() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 500);
        }
    }

    protected void stopLoadMore() {
        if (recycleView != null) {
            recycleView.loadMoreComplete();
        }
    }

    protected View getHeaderView() {
        return null;
    }
}
