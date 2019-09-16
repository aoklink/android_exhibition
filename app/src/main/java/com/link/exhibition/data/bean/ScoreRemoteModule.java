package com.link.exhibition.data.bean;

import java.util.List;

/**
 * Created on 2019/9/9  16:49
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class ScoreRemoteModule {

    /**
     * type : 203
     * uid : **
     * score : 70
     */

    private int type;
    private String uid;
    private String score;
    private List<UserRemoteModule> rank_data;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getScore() {
        return score == null ? "" : score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<UserRemoteModule> getRank_data() {
        return rank_data;
    }

    public void setRank_data(List<UserRemoteModule> rank_data) {
        this.rank_data = rank_data;
    }
}
