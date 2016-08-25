package com.vv.mydesignframework.hybrid;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.vv.mydesignframework.base.WebActivity;

/**
 * Created by VV on 2016/8/24.
 */
public class HybridChromeClient extends WebChromeClient {
    private WebActivity act;

    public HybridChromeClient(WebActivity act) {
        this.act = act;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onProgressChanged(final WebView view, int newProgress) {
        //与精度相关的业务逻辑可以在这里做
        super.onProgressChanged(view, newProgress);
    }
}
