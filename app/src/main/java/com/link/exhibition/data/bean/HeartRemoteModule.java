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

    public String getResultStr() {
        if (heart_rate_ratio < 1) {
            return "";
        }
        if (heart_rate_ratio <= 39) {
            return "激活放松";
        } else if (heart_rate_ratio <= 55) {
            return "动态热身";
        } else if (heart_rate_ratio <= 69) {
            return "脂肪燃烧";
        } else if (heart_rate_ratio <= 79) {
            return "有氧耐力";
        } else if (heart_rate_ratio <= 89) {
            return "无氧耐力";
        } else if (heart_rate_ratio <= 99) {
            return "峰值锻炼";
        }
        return "峰值锻炼";
    }

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
