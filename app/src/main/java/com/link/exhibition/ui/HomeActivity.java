package com.link.exhibition.ui;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.link.exhibition.R;
import com.link.exhibition.constants.ColorConstants;
import com.link.exhibition.data.bean.HeartRemoteModule;
import com.link.exhibition.data.bean.HomeRemoteModule;
import com.link.exhibition.data.bean.OffsetModule;
import com.link.exhibition.data.bean.ScoreRemoteModule;
import com.link.exhibition.data.bean.UnbindRemoteModule;
import com.link.exhibition.data.bean.UserRemoteModule;
import com.link.exhibition.framework.base.EmptyMvpPresenter;
import com.link.exhibition.framework.base.FrameworkBaseActivity;
import com.link.exhibition.framework.component.image.LinkImageLoader;
import com.link.exhibition.framework.component.image.transformation.CircleTransform;
import com.link.exhibition.framework.component.mqtt.MqttManager;
import com.link.exhibition.framework.utils.data.L;
import com.link.exhibition.framework.utils.data.StringUtils;
import com.link.exhibition.framework.widgets.NumParseUtil;
import com.link.exhibition.mvp.common.MvpPresenter;
import com.link.exhibition.utils.CollectionsUtil;
import com.link.exhibition.utils.DateUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created on 2019/1/14  18:58
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class HomeActivity extends FrameworkBaseActivity implements MqttCallbackExtended {

    public static List<OffsetModule> sOffsetCache = new ArrayList<>();

    @BindView(R.id.user1_cl)
    ConstraintLayout mTemple1Cl;
    @BindView(R.id.user1_avatar)
    ImageView mTemple1Avatar;
    @BindView(R.id.date)
    TextView mTvDate;
    @BindView(R.id.user1_name)
    TextView mTemple1Name;
    @BindView(R.id.user1_heart)
    TextView mTemple1Heart;
    @BindView(R.id.user1_score)
    TextView mTemple1Score;
    @BindView(R.id.user1_wave)
    WaveView mTemple1Wave;

    @BindView(R.id.user2_cl)
    ConstraintLayout mTemple2Cl;
    @BindView(R.id.user2_avatar)
    ImageView mTemple2Avatar;
    @BindView(R.id.user2_name)
    TextView mTemple2Name;
    @BindView(R.id.user2_heart)
    TextView mTemple2Heart;
    @BindView(R.id.user2_score)
    TextView mTemple2Score;
    @BindView(R.id.user2_wave)
    WaveView mTemple2Wave;

    @BindView(R.id.score_rv)
    RecyclerView mRvRank;

    private CircleTransform mCircleTransform;

    private List<UserRemoteModule> mCacheModules;

    private List<UserRemoteModule> mRankModules;

    private RankAdapter mRankAdapter;

    private OffsetModule mOffset;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mCircleTransform = new CircleTransform(this);
        mCacheModules = new ArrayList<>();
        mRankModules = new ArrayList<>();
        MqttManager.getInstance().connect(this);

        mTvDate.setText(DateUtils.getDateForEnglish(System.currentTimeMillis()));

        sOffsetCache.add(0, new OffsetModule());
        sOffsetCache.add(1, new OffsetModule());

        mCacheModules.add(0, new UserRemoteModule());
        mCacheModules.add(1, new UserRemoteModule());

        initRecyclerView();

        mTemple1Cl.setBackgroundColor(ColorConstants.loadColor(0));
        mTemple1Wave.initWave(true, ColorConstants.loadColors(0));

        mTemple2Cl.setBackgroundColor(ColorConstants.loadColor(40));
        mTemple2Wave.initWave(true, ColorConstants.loadColors(40));

        initRank(null);
    }

    private void initRankModules() {
        if (mRankModules == null) {
            mRankModules = new ArrayList<>();
        }
        mRankModules.clear();
        mRankModules.add(new UserRemoteModule());
        mRankModules.add(new UserRemoteModule());
        mRankModules.add(new UserRemoteModule());
        mRankModules.add(new UserRemoteModule());
        mRankModules.add(new UserRemoteModule());
    }

    private void initRecyclerView() {
        mRankAdapter = new RankAdapter(this);
        mRvRank.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRvRank.setAdapter(mRankAdapter);
        mRankAdapter.setModules(mRankModules);
    }


    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new EmptyMvpPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        if (reconnect) {
            MqttManager.getInstance().subscribeToTopic();
        }
        L.e("HomeActivity", "reconnect:" + reconnect);
    }

    @Override
    public void connectionLost(Throwable cause) {
        L.e("HomeActivity", "close:connectionLost", cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        // 200	手环绑定(server->client)
        // 201	手环解绑(server->client)
        // 202	心率变化(server->client)
        // 203	新测试用户(server->client)
        // 204	更新评分(server->client)
        // 210	返回排行榜和当前所有锻炼用户(server->client)
        String body = new String(message.getPayload());
        JSONObject object = JSONObject.parseObject(body);
        int type = object.getIntValue("type");
        switch (type) {
            case 201:
                notifyUnbindChanged(JSON.parseObject(body, UnbindRemoteModule.class));
                break;
            case 202:
                notifyHeartChanged(JSON.parseObject(body, HeartRemoteModule.class));
                break;
            case 203:
                notifyInsertChanged(JSON.parseObject(body, UserRemoteModule.class));
                break;
            case 204:
                notifyScoreChanged(JSON.parseObject(body, ScoreRemoteModule.class));
                break;
            case 210:
                notifyRankChanged(JSON.parseObject(body, HomeRemoteModule.class));
                break;
        }
        L.e("HomeActivity", "messageArrived:" + body);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }


    /**
     * 评分变化
     *
     * @param score
     */
    private void notifyScoreChanged(ScoreRemoteModule score) {
        initRank(score == null ? null : score.getRank_data());
        if (mCacheModules.get(0).getUid().equals(score.getUid())) {
            mTemple1Score.setTextSize(NumParseUtil.parseFloat(score.getScore()) > 0 ? 37 : 18);
            mTemple1Score.setText(score.getScore());
            return;
        }
        if (mCacheModules.get(1).getUid().equals(score.getUid())) {
            mTemple2Score.setTextSize(NumParseUtil.parseFloat(score.getScore()) > 0 ? 37 : 18);
            mTemple2Score.setText(score.getScore());
        }
    }


    /**
     * 新增用户
     *
     * @param module
     */
    private void notifyInsertChanged(UserRemoteModule module) {
        if (StringUtils.isEmpty(mCacheModules.get(0).getUid())) {
            mCacheModules.get(0).update(module);
            updateUser1(mCacheModules.get(0));
            return;
        }
        if (StringUtils.isEmpty(mCacheModules.get(1).getUid())) {
            mCacheModules.get(1).update(module);
            updateUser2(mCacheModules.get(1));
        }
    }

    /**
     * 手环解绑
     *
     * @param module
     */
    private void notifyUnbindChanged(UnbindRemoteModule module) {
        if (mCacheModules.get(0).getUid().equals(module.getUid())) {
            mCacheModules.get(0).reset();
            updateUser1(mCacheModules.get(0));
            mTemple1Cl.setBackgroundColor(ColorConstants.loadColor(0));
            mTemple1Wave.initWave(true, ColorConstants.loadColors(0));
            return;
        }
        if (mCacheModules.get(1).getUid().equals(module.getUid())) {
            mCacheModules.get(1).reset();
            updateUser2(mCacheModules.get(1));
            mTemple2Cl.setBackgroundColor(ColorConstants.loadColor(40));
            mTemple2Wave.initWave(true, ColorConstants.loadColors(40));
        }
    }


    /**
     * 心率变化
     *
     * @param heart
     */
    private void notifyHeartChanged(HeartRemoteModule heart) {
        if (mCacheModules.get(0).getUid().equals(heart.getUid())) {
            mTemple1Heart.setText(heart.getHeart_rate());

            mOffset = HomeActivity.sOffsetCache.get(0);
            mTemple1Cl.setBackgroundColor(ColorConstants.loadColor(heart.getHeart_rate_ratio()));
            mTemple1Wave.initValueManager(0, mOffset.getOffset1(), mOffset.getOffset2(), mOffset.getOffset3(), ColorConstants.loadColors(heart.getHeart_rate_ratio()));
            return;
        }

        if (mCacheModules.get(1).getUid().equals(heart.getUid())) {
            mTemple2Heart.setText(heart.getHeart_rate());

            mOffset = HomeActivity.sOffsetCache.get(1);
            mTemple2Cl.setBackgroundColor(ColorConstants.loadColor(heart.getHeart_rate_ratio()));
            mTemple2Wave.initValueManager(1, mOffset.getOffset1(), mOffset.getOffset2(), mOffset.getOffset3(), ColorConstants.loadColors(heart.getHeart_rate_ratio()));
        }
    }

    /**
     * 初始化
     *
     * @param module
     */
    private void notifyRankChanged(HomeRemoteModule module) {
        if (module == null) {
            initRank(null);
            return;
        }
        initRank(module.getRank_data());
        // 初始化模板
        List<UserRemoteModule> list = module.getBind_data();
        if (CollectionsUtil.isNotEmpty(list)) {
            if (CollectionsUtil.size(list) > 0 && list.get(0) != null) {
                UserRemoteModule user = list.get(0);
                updateUser1(user);
            }

            if (CollectionsUtil.size(list) > 1) {
                UserRemoteModule user = list.get(1);
                updateUser2(user);
            }
        }
    }

    private void initRank(List<UserRemoteModule> ranks) {
        // 初始化排行榜
        initRankModules();
        if (CollectionsUtil.isEmpty(ranks)) {
            mRankAdapter.setModules(mRankModules);
            return;
        }
        if (CollectionsUtil.size(ranks) <= 5) {
            for (int i = 0; i < CollectionsUtil.size(ranks); i++) {
                mRankModules.get(i).update(ranks.get(i));
            }
        } else {
            ranks = ranks.subList(0, 5);
            for (int i = 0; i < CollectionsUtil.size(ranks); i++) {
                mRankModules.get(i).update(ranks.get(i));
            }
        }
        mRankAdapter.setModules(mRankModules);
    }

    private void updateUser2(UserRemoteModule user) {
        mTemple2Cl.setBackgroundColor(ColorConstants.loadColor(user.getRatio()));
        LinkImageLoader.INSTANCE.load(user.getHead_icon(), mTemple2Avatar, mCircleTransform);
        mTemple2Name.setText(user.getUser_name());
        mTemple2Heart.setText(user.getHeart_rate());
        mTemple2Score.setTextSize(TypedValue.COMPLEX_UNIT_SP, user.isScore() ? 37 : 18);
        mTemple2Score.setText(user.getScore());
        OffsetModule mInflateOffset = HomeActivity.sOffsetCache.get(1);

        mTemple2Cl.setBackgroundColor(ColorConstants.loadColor(user.getRatio()));
        mTemple2Wave.initValueManager(1, mInflateOffset.getOffset1(), mInflateOffset.getOffset2(), mInflateOffset.getOffset3(), ColorConstants.loadColors(user.getRatio()));
        mCacheModules.get(1).update(user);
    }

    private void updateUser1(UserRemoteModule user) {
        mTemple1Cl.setBackgroundColor(ColorConstants.loadColor(user.getRatio()));
        LinkImageLoader.INSTANCE.load(user.getHead_icon(), mTemple1Avatar, mCircleTransform);
        mTemple1Name.setText(user.getUser_name());
        mTemple1Heart.setText(user.getHeart_rate());
        mTemple1Score.setTextSize(TypedValue.COMPLEX_UNIT_SP, user.isScore() ? 37 : 18);
        mTemple1Score.setText(user.getScore());
        OffsetModule mInflateOffset = HomeActivity.sOffsetCache.get(0);

        mTemple1Cl.setBackgroundColor(ColorConstants.loadColor(user.getRatio()));
        mTemple1Wave.initValueManager(0, mInflateOffset.getOffset1(), mInflateOffset.getOffset2(), mInflateOffset.getOffset3(), ColorConstants.loadColors(user.getRatio()));
        mCacheModules.get(0).update(user);
    }


}
