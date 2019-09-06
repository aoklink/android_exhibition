package com.link.feeling.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.link.feeling.R;
import com.link.feeling.framework.base.EmptyMvpPresenter;
import com.link.feeling.framework.base.FrameworkBaseActivity;
import com.link.feeling.mvp.common.MvpPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.jzvd.JzvdStd;

/**
 * Created on 2019/1/14  18:58
 * chenpan pan.chen@linkfeeling.cn
 */
public final class HomeActivity extends FrameworkBaseActivity{

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);

    }


    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new EmptyMvpPresenter();
    }

    public static void launchPage(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
