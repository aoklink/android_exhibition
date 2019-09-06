package com.link.exhibition.framework.base;

import com.link.exhibition.mvp.common.MvpPresenter;
import com.link.exhibition.mvp.common.MvpView;

/**
 * Created on 2019/1/14  16:17
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public interface BaseMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void initSuccess();

}
