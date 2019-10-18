package com.link.exhibition.data.bean;

import com.link.exhibition.framework.utils.data.StringUtils;
import com.link.exhibition.utils.Base64Utils;

/**
 * Created on 2019/9/9  14:47
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class UserRemoteModule {


    /**
     * uid : *
     * user_name : xx
     * head_icon : xx
     * score : 80
     */

    private String uid;
    private String user_name;
    private String head_icon;
    private String score;
    private int heart_rate_ratio;
    private String heart_rate;

     public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name == null ? "" : Base64Utils.URLDecoder(user_name);
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_icon() {
        return head_icon == null ? "" : head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getScore() {
        return StringUtils.isEmpty(score) ? "" : score;
    }
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

    public String getScore1() {
        return score == null ? "-" : score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHeart_rate() {
        return heart_rate == null ? "-" : heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public int getRatio() {
        return heart_rate_ratio;
    }

    public void setRatio(int ratio) {
        this.heart_rate_ratio = ratio;
    }

    public void reset() {
        this.uid = "";
        this.user_name = "";
        this.head_icon = "";
        this.score = "-";
        this.heart_rate = "-";
        this.heart_rate_ratio = 0;
    }

    public void update(UserRemoteModule module) {
        this.uid = module.getUid();
        this.user_name = module.getUser_name();
        this.head_icon = module.getHead_icon();
        this.score = module.getScore();
        this.heart_rate = module.getHeart_rate();
        this.heart_rate_ratio = module.getRatio();
    }
}
