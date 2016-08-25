package com.vv.mydesignframework.hybrid;

import com.vv.mydesignframework.base.WebActivity;

/**
 * Created by VV on 2016/8/24.
 */
public class WebHandlerFactory {
    private WebActivity activity;

    public WebHandlerFactory(WebActivity activity) {
        this.activity = activity;
    }

    public WebHandler createHybridHandler(String str) {
        // 先从 集合中取 如果没有去创建对象
        WebHandler webHandler = activity.getWebHandlerMap().get(str);
        if (webHandler != null) {
            return webHandler;
        }

        //创建 url处理对象
        if (str.equals(HybridConstans.URL_TASK)) {
            UrlHnadler mUrlHnadler = new UrlHnadler();
            activity.addHybridHandler(mUrlHnadler.getHandlerName(),mUrlHnadler);
            return mUrlHnadler;
        }
        //创建 自定义消息处理对象
        if (str.equals(HybridConstans.URL_TASK)) {
            CustomWebHandler mCustomWebHandler = new CustomWebHandler(activity);
            activity.addHybridHandler(mCustomWebHandler.getHandlerName(), mCustomWebHandler);
            return mCustomWebHandler;
        }

        return null;
    }
}
