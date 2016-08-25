package com.vv.mvp.mvp;

import android.graphics.Bitmap;

/**
 * Created by VV on 2016/8/21.
 */
public class PP extends BasePresenter {
    //需要持有view和model的引用

    public PP(BaseView view) {
        super(view);
    }

    @Override
    public void fetch() {
        VIEW.onLoading();
        MODEL.loadData(new BaseModel.ModelCallback<Bitmap>() {

            @Override
            public void onComplete(Bitmap data) {
                VIEW.showData(data);
            }
        });
    }
}
