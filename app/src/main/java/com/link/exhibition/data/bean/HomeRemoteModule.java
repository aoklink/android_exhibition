package com.link.exhibition.data.bean;

import com.link.exhibition.utils.CollectionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/9/9  14:46
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class HomeRemoteModule {
    private int type;
    private List<UserRemoteModule> rank_data;
    private List<UserRemoteModule> bind_data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<UserRemoteModule> getRank_data() {
        if (CollectionsUtil.isEmpty(rank_data)){
            rank_data = new ArrayList<>();
        }
        return rank_data;
    }

    public void setRank_data(List<UserRemoteModule> rank_data) {
        this.rank_data = rank_data;
    }

    public List<UserRemoteModule> getBind_data() {
        return bind_data;
    }

    public void setBind_data(List<UserRemoteModule> bind_data) {
        this.bind_data = bind_data;
    }
}
