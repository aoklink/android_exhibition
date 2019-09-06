package com.link.exhibition.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.link.exhibition.R;
import com.link.exhibition.framework.base.EmptyMvpPresenter;
import com.link.exhibition.framework.base.FrameworkBaseActivity;
import com.link.exhibition.mvp.common.MvpPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
