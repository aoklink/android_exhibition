package com.link.exhibition.application;

import com.link.exhibition.BuildConfig;
import com.link.exhibition.framework.base.BaseApplication;
import com.link.exhibition.framework.component.net.config.ServerConfig;

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
