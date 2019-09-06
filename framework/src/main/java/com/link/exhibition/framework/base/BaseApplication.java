package com.link.exhibition.framework.base;

import android.app.Application;
import android.content.Context;
import com.link.exhibition.framework.utils.ui.ActivityUtils;
/**
 * Created on 2019/1/3  15:06
 * chenpan pan.chen@linkfeeling.cn
 */
public class BaseApplication extends Application {

    // context
    private static BaseApplication sContext;
    // uid
    public static String sUID;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        ActivityUtils.init(this);
    }

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onLowMemory() {
        System.gc();
        System.runFinalization();
        System.gc();
        super.onLowMemory();
    }
}
