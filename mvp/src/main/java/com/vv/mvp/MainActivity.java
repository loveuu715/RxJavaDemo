package com.vv.mvp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.vv.mvp.mvp.BaseView;

public class MainActivity extends AppCompatActivity implements BaseView<Bitmap> {

    private ImageView mTvDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvDatas = (ImageView) findViewById(R.id.tv_data);
//        new PP(this).fetch();
    }

    public void srcRes(View view) {
        mTvDatas.setImageResource(R.mipmap.pic);
    }

    public void javaRes(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvDatas.setImageBitmap(BitmapUtil.getBitmapJava(
                        BitmapFactory.decodeResource(getResources(), R.mipmap.pic)));
            }
        });
    }

    public void ndkRes(View view) {

    }


    @Override
    public void onLoading() {
        Toast.makeText(MainActivity.this, "正在加载。。。", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(Bitmap data) {
        mTvDatas.setImageBitmap(data);
    }
}
