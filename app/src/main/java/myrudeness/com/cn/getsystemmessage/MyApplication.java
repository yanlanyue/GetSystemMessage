package myrudeness.com.cn.getsystemmessage;

import android.app.Application;

import myrudeness.com.cn.getsystemmessage.util.UEHandler;

/**
 * Created by yanjunlin on 2017/8/31 16:12.
 */

public class MyApplication extends Application {
    /**
     * MyApplication实例
     */
    private static MyApplication instance;

    /**
     * 异常处理类
     */
    private UEHandler ueHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置异常处理实例
        ueHandler = new UEHandler();
        Thread.setDefaultUncaughtExceptionHandler(ueHandler);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        System.gc();
    }
}
