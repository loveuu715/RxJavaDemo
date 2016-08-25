package com.vv.mydesignframework.common;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Activity堆栈管理
 * <p>
 * Created by VV on 2016/8/23.
 */
public class ActivityTaskManager {

    private static Stack<Activity> sActivityStack;
    private static volatile ActivityTaskManager sTaskManager;

    private ActivityTaskManager() {
        sActivityStack = new Stack<Activity>();
    }

    public static ActivityTaskManager getInstance() {
        if (sTaskManager == null) {
            synchronized (ActivityTaskManager.class) {
                if (sTaskManager == null) {
                    sTaskManager = new ActivityTaskManager();
                }
            }
        }
        return sTaskManager;
    }

    /**
     * 添加一个Activity到堆栈中
     * @param activity
     */
    public void add(Activity activity) {
        if (activity != null) {
            sActivityStack.add(activity);
        }
    }

    /**
     * 从堆栈中移除但不关闭
     * @param activity
     */
    public void removeActivity(Activity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 从堆栈中关闭移除第一个Activity
     */
    public void finishActivity() {
        finishActivity(sActivityStack.lastElement());
    }

    /**
     * 从堆栈中移除并关闭Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 从堆栈中移除并关闭Activity
     * @param clazz
     */
    public void finishActivity(Class<?> clazz) {
        if (clazz != null) {
            for (Activity activity : sActivityStack) {
                if (activity.getClass().equals(clazz)) {
                    finishActivity(activity);
                }
            }
        }
    }

    /**
     * 从堆栈中移除并关闭所有的Activity
     */
    public void finishActivities() {
        for (Activity activity : sActivityStack) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        sActivityStack.clear();
    }

    /**
     * 从堆栈中获得第一个Activity
     * @return
     */
    public Activity getCurrentActivity() {
        return sActivityStack.lastElement();
    }

    /**
     * 退出应用
     * @param context 上下文
     * @param isClearCache 是否清除缓存
     */
    public void exit(Context context, boolean isClearCache) {
        finishActivities();
        if (isClearCache) {
            //清除缓存
            CacheManager.clearCache();
        }

//        System.exit(0);
//        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
