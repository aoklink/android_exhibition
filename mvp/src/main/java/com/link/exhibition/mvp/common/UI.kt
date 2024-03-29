package com.link.exhibition.mvp.common

import androidx.annotation.UiThread

/**
 * Created on 2019/1/11  15:17
 * chenpan pan.chen@linkfeeling.cn
 */
interface UI {
    @UiThread
    fun showLoading()

    @UiThread
    fun showError(throwable: Throwable)

    @UiThread
    fun showContent()
}