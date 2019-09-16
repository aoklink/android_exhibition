package com.link.exhibition.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.link.exhibition.R;
import com.link.exhibition.constants.ImageConstants;
import com.link.exhibition.data.bean.UserRemoteModule;
import com.link.exhibition.framework.base.BaseViewHolder;
import com.link.exhibition.framework.component.image.LinkImageLoader;
import com.link.exhibition.framework.component.image.transformation.CircleTransform;
import com.link.exhibition.framework.utils.data.DisplayUtils;
import com.link.exhibition.utils.CollectionsUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created on 2019/9/10  16:37
 * chenpan pan.chen@linkfeeling.cn
 */
public final class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<UserRemoteModule> mModules;

    private CircleTransform mCircleTransform;

    private float mItemWidth = (float) ((DisplayUtils.getScreenWidth()*0.83 - DisplayUtils.dp2px(60)) / 5);

    public RankAdapter(Context mContext) {
        this.mContext = mContext;
        mCircleTransform = new CircleTransform(mContext);
    }

    public void setModules(List<UserRemoteModule> mModules) {
        this.mModules = mModules;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RankHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserRemoteModule module = mModules.get(position);
        RankHolder rankHolder = (RankHolder) holder;

        rankHolder.mIvRank.setImageResource(ImageConstants.matchRankImage(position));
        LinkImageLoader.INSTANCE.load(module.getHead_icon(), rankHolder.mRankAvatar, mCircleTransform);

        rankHolder.mRankScore.setText(module.getScore1());
        rankHolder.mRankName.setText(module.getUser_name());
    }

    @Override
    public int getItemCount() {
        return CollectionsUtil.size(mModules);
    }


    class RankHolder extends BaseViewHolder {
        @BindView(R.id.rank_iv)
        ImageView mIvRank;
        @BindView(R.id.rank_avatar)
        ImageView mRankAvatar;
        @BindView(R.id.rank_score)
        TextView mRankScore;
        @BindView(R.id.rank_name)
        TextView mRankName;

        RankHolder(View itemView) {
            super(itemView);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.width = (int) mItemWidth;
        }

    }
}
