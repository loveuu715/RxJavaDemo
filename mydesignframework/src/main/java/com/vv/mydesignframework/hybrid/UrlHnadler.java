package com.vv.mydesignframework.hybrid;

import android.content.Intent;
import android.net.Uri;

import com.vv.mydesignframework.base.WebActivity;

/**
 * Created by VV on 2016/8/24.
 */
public class UrlHnadler implements WebHandler {

    @Override
    public String getHandlerName() {
        return HybridConstans.URL_TASK;
    }

    @Override
    public boolean handerTask(WebActivity mActivity, String url) {
        if (url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mActivity.startActivity(intent);
            return true;
        } else if (url.startsWith("smsto:")) {
            Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
            it.putExtra("sms_body", "");
            mActivity.startActivity(it);
            return true;
        } else if (url.startsWith("mailto:")) {
            Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
            mActivity.startActivity(it);
            return true;
        } else if (url.startsWith("mqqwpa:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mActivity.startActivity(intent);
            return true;
        } else if (url.startsWith("http://") || url.startsWith("https://")) {
            mActivity.getWebView().loadUrl(url);
        }
        return false;
    }
}
