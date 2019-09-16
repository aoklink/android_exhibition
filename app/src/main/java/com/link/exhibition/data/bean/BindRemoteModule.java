package com.link.exhibition.data.bean;

/**
 * Created on 2019/9/9  16:52
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class BindRemoteModule {

    /**
     * type : 200
     * uid : **
     * user_name : xxxx
     * head_icon : xxxx
     * heart_rate : 80
     */

    private int type;
    private String uid;
    private String user_name;
    private String head_icon;
    private int heart_rate;

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }
}
