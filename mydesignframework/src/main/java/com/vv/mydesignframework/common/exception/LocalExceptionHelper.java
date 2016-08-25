package com.vv.mydesignframework.common.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.vv.mydesignframework.utils.ThreadUtil;


/**
 * 异常信息处理及保存
 * Created by Wayne on 2016/7/21.
 */
public class LocalExceptionHelper extends ExceptionHelper {

    private Context mContext;

    public LocalExceptionHelper(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        ThreadUtil.execut(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉, 应用出现异常，正在从异常中恢复...", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });
        saveLog(new SaveOnCacheDir(mContext), ex);
        return true;
    }

    /**
     * 保存异常日志
     * @param saveModel
     * @param throwable
     */
    private void saveLog(SaveModel saveModel, Throwable throwable) {
        saveModel.onSave(throwable);
    }

}
