package com.link.exhibition.framework.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.link.exhibition.framework.R;
import com.link.exhibition.framework.utils.data.DisplayUtils;
import com.link.exhibition.framework.utils.data.ToastUtils;
import com.link.exhibition.framework.utils.ui.ActivityUtils;
import com.link.exhibition.framework.utils.ui.KeyboardUtils;
import com.link.exhibition.framework.utils.ui.ViewUtils;
import com.link.exhibition.mvp.MvpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import butterknife.ButterKnife;

/**
 * Created on 2019/1/14  16:35
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public abstract class FrameworkBaseActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends MvpActivity<V, P> {

    private boolean mAutoCancelToast = false;
    private boolean mIsInit = false;
    public SVProgressHUD mProgressHUD;
    private final static Handler MAIN_HANDLER = new MainHandler();
    // 延迟处理事件
    private static final int WHAT_DELAY = 111;
    private boolean mLoadingEnable = true;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        // 透明状态栏
        DisplayUtils.translucentStatusBar(this);
        ButterKnife.bind(this);
        if (getPresenter() == null) {
            finish();
            return;
        }
        final View contentView = ActivityUtils.getActivityContentView(this);
        if (contentView != null) {
            contentView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return FrameworkBaseActivity.this.onPreDraw();
                }
            });
        }
        mIsInit = false;
        if (mProgressHUD == null) {
            mProgressHUD = new SVProgressHUD(this);
        }
        init(savedInstanceState);
        mIsInit = true;
        getPresenter().initSuccess();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 关闭软键盘
        KeyboardUtils.hideSoftInput(this);
        if (mProgressHUD != null && mProgressHUD.isShowing()) {
            mProgressHUD.dismiss();
            mProgressHUD.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
//        SwipeBackHelper.onDestroy(this);
        if (mAutoCancelToast) {
            ToastUtils.cancel();
        }
        MAIN_HANDLER.removeMessages(WHAT_DELAY);
        KeyboardUtils.fixSoftInputLeaks(this);
        super.onDestroy();
    }

    public boolean isInit() {
        return mIsInit;
    }

    /**
     * 显示 Toast
     *
     * @param text text
     */
    @UiThread
    public final void showToast(@NonNull final String text) {
        ToastUtils.showToast(text);
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public final Context getContext() {
        return this;
    }

    /**
     * 显示 Toast
     *
     * @param text text
     */
    @UiThread
    protected void showToast(@StringRes final int text) {
        ToastUtils.showToast(text);
    }

    /**
     * 显示错误信息
     *
     * @param errorMsg 错误信息
     */
    public void showErrorStatus(String errorMsg) {
        mProgressHUD.showErrorWithStatus(errorMsg);
        postDelay(() -> mProgressHUD.dismiss(), 800);
    }

    /**
     * 显示正常信息
     *
     * @param msg 信息
     */
    public void showNormalStatus(String msg) {
        mProgressHUD.showInfoWithStatus(msg);
    }

    /**
     * 显示加载
     *
     * @param
     */
    public void showLoadingStatus() {
        mProgressHUD.showWithStatus(ViewUtils.getString(R.string.loading), SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    /**
     * 隐藏加载
     *
     * @param
     */
    public void hideLoadingStatus() {
        mProgressHUD.dismiss();
    }

    /**
     * 是否在 Activity 关闭时取消 Toast
     *
     * @param autoCancelToast 自动取消 Toast
     */
    protected final void setAutoCancelToast(boolean autoCancelToast) {
        mAutoCancelToast = autoCancelToast;
    }

    /**
     * 获取布局
     *
     * @return layout res
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化，在 Presenter 准备完毕
     *
     * @param savedInstanceState bundle
     */
    protected abstract void init(@Nullable Bundle savedInstanceState);

    /**
     * 在 ContentView onPreDraw 之前调用，在这里可以获取控件的测量尺寸
     * {@link View#getMeasuredWidth()} 和 {@link View#getMeasuredHeight()}
     */
    protected boolean onPreDraw() {
        return true;
    }

    /**
     * 延迟执行事件
     *
     * @param runnable    执行
     * @param delayMillis 延时
     */
    protected void postDelay(Runnable runnable, long delayMillis) {
        if (runnable == null) {
            return;
        }
        MAIN_HANDLER.sendMessageDelayed(
                MAIN_HANDLER.obtainMessage(WHAT_DELAY, runnable),
                delayMillis
        );
    }

    protected boolean isCountFragment() {
        return false;
    }

    protected void loadingEnable(boolean enable) {
        mLoadingEnable = enable;
    }

    private static class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_DELAY && msg.obj instanceof Runnable) {
                ((Runnable) msg.obj).run();
            }
        }
    }

}

