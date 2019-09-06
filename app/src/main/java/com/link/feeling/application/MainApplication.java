package com.link.feeling.application;

import com.link.feeling.BuildConfig;
import com.link.feeling.framework.base.BaseApplication;
import com.link.feeling.framework.component.net.config.ServerConfig;

/**
 * Created on 2019/4/12  11:15
 * chenpan pan.chen@linkfeeling.cn
 */
public final class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        ServerConfig.initEnv(BuildConfig.ENV, BuildConfig.DEBUG);
    }
}
