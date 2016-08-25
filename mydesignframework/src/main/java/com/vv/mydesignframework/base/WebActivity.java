package com.vv.mydesignframework.base;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.vv.mydesignframework.R;
import com.vv.mydesignframework.hybrid.HybridChromeClient;
import com.vv.mydesignframework.hybrid.HybridInterface;
import com.vv.mydesignframework.hybrid.HybridWebViewClient;
import com.vv.mydesignframework.hybrid.WebHandler;

import java.io.File;
import java.util.HashMap;

/**
 * Created by VV on 2016/8/24.
 */
public class WebActivity extends BaseActivity {
    private ViewGroup wbContainer;
    private WebView webView;
    /**
     * 维护 整个webview 交互处理事件的 WebHandler对象；
     */
    private HashMap<String, WebHandler> mWebHandlerHashMap = new HashMap<>();


    @Override
    protected void initInjector() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }


    @Override
    public void initView() {
        wbContainer = (ViewGroup) findViewById(R.id.wbContainer);
        webView = new WebView(this);
        wbContainer.addView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setInitialScale(80);
        webView.setHorizontalScrollbarOverlay(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setWebViewClient(new HybridWebViewClient(this));
        webView.addJavascriptInterface(new HybridInterface(this), "app");
        webView.setWebChromeClient(new HybridChromeClient(this));
        webView.loadUrl("url");


    }

    @Override
    public void initEvent() {

    }

    public HashMap<String, WebHandler> getWebHandlerMap() {
        if(mWebHandlerHashMap == null){
            mWebHandlerHashMap = new HashMap<>();
        }
        return mWebHandlerHashMap;
    }

    /**
     * 将 HybridHandler 添加到这个集合
     *
     * @param evenName
     * @param webHandler
     */
    public void addHybridHandler(String evenName, WebHandler webHandler) {
        if (mWebHandlerHashMap != null) {
            mWebHandlerHashMap.put(evenName, webHandler);
        }
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    public void setMyTitle(String title) {
//        TitleUtils.setTitle(WebActivity.this, title);
    }

    /**
     * 设置标题栏 左边view 是否可见
     */
    public void setLeftVisibility(int visibility) {
//        TitleUtils.getLeft(this).setVisibility(visibility);
    }

    /**
     * 设置标题栏 右边view 是否可见
     */
    public void setRightVisibility(int visibility) {
//        TitleUtils.getRight(this).setVisibility(visibility);
    }

    /**
     * app 调用 js 代码
     *
     * @param url
     */
    public void App2JsCode(String url) {
        webView.loadUrl(url);
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public void finish() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        mWebHandlerHashMap.clear();
        mWebHandlerHashMap = null;

        File file = this.getCacheDir();
        if (null != file) {
            deleteFile(file);
        }
        webView.removeJavascriptInterface("app");
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
//        mContext.deleteDatabase("webview.db");
//       mContext.deleteDatabase("webviewCache.db");
        webView.destroy();
        super.onDestroy();
    }

    private boolean deleteFile(File file) {
        if (file == null)
            return false;
        if (file.isFile()) {
            return file.delete();
        } else {
            File[] files = file.listFiles();
            if (files != null && files.length != 0)
                for (File f : files) {
                    if (!deleteFile(f)) {
                        return false;
                    }
                }
            file.delete();
        }
        return true;
    }
}
