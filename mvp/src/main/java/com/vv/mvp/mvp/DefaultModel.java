package com.vv.mvp.mvp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vv.mvp.BitmapUtil;
import com.vv.mvp.R;

/**
 * Created by VV on 2016/8/21.
 */
public class DefaultModel implements BaseModel<Bitmap> {
    @Override
    public void loadData(ModelCallback<Bitmap> listener) {
        listener.onComplete(BitmapUtil.getBitmapJava(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.pic)));
    }
}
