package com.link.exhibition.framework.bean;

/**
 * Created on 2019/9/9  14:49
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class HomeRequest {

    private int type = 100;
    private String gym_id = "link_zfb";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGym_id() {
        return gym_id == null ? "" : gym_id;
    }

    public void setGym_id(String gym_id) {
        this.gym_id = gym_id;
    }
}
