package com.vv.mvp.mvp;

import java.util.List;

/**
 * Created by VV on 2016/8/21.
 */
public class MyPresenter {
    //持有model和view的引用
    BaseModel mIModel = new MyModel();
    BaseView mIView;

    public MyPresenter(BaseView IView) {
        mIView = IView;
    }

    public void fetch(){
        mIView.onLoading();
        mIModel.loadData(new BaseModel.ModelCallback<List<String>>() {
            @Override
            public void onComplete(List<String> data) {
                mIView.showData(data);
            }
        });
    }
}
