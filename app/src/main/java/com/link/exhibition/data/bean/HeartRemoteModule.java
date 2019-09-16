package com.link.exhibition.data.bean;

/**
 * Created on 2019/9/9  16:51
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class HeartRemoteModule {


    /**
     * type : 202
     * uid : **
     * heart_rate : 85
     */

    private int type;
    private int heart_rate_ratio;
    private String uid;
    private String heart_rate;

    public int getHeart_rate_ratio() {
        return heart_rate_ratio;
    }

    public void setHeart_rate_ratio(int heart_rate_ratio) {
        this.heart_rate_ratio = heart_rate_ratio;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }
}
