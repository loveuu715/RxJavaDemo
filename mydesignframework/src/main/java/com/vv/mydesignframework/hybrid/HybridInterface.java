package com.vv.mydesignframework.hybrid;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.vv.mydesignframework.base.WebActivity;
import com.vv.mydesignframework.utils.TipUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by VV on 2016/8/24.
 */
public class HybridInterface {
    private WebActivity mActivity;
    //处理事件的名字
    private final String EVEN_NAME = "evenName";
    //具体处理事件的内容，此字段不使用，只是打印
    private final String MSG = "msg";
    public HybridInterface(WebActivity mActivity) {
        this.mActivity = mActivity;
    }
    /**
     * html5 调用native App 统一的方法
     *
     * @param string
     */
    @JavascriptInterface
    public void nativeHanderTask(final String string) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("nativeHanderTask", string + "");
                String evenName = "";
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    evenName = jsonObject.getString(EVEN_NAME);
                    evenName = jsonObject.getString(EVEN_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WebHandler mHybridHandler = new WebHandlerFactory(mActivity).createHybridHandler(evenName);
                if (mHybridHandler != null) {
                    boolean is_handerl =  mHybridHandler.handerTask(mActivity,string);
                    if( !is_handerl){
                        TipUtil.showToast("App没有处理", Toast.LENGTH_LONG);
                    }
                }else{
                    TipUtil.showToast("App没有处理事件的--WebHandler", Toast.LENGTH_LONG);
                }

            }
        });

    }
}
