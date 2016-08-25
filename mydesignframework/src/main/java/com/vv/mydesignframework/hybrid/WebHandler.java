package com.vv.mydesignframework.hybrid;

import com.vv.mydesignframework.base.WebActivity;

/**
 * Created by VV on 2016/8/24.
 */
public interface WebHandler {
    /**
     * 处理事件对象的名称
     * @return
     */
    String getHandlerName();

    /**
     *  对应的实现类 处理对应的事件任务  返回true 带表处理了， false 则是没有处理
     * @param string
     * @return
     */
    boolean handerTask(WebActivity mActivity, String string);
}
