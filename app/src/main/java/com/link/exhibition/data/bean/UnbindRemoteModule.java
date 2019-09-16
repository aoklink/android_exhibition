package com.link.exhibition.data.bean;

/**
 * Created on 2019/9/10  16:08
 * chenpan pan.chen@linkfeeling.cn
 */
public final class UnbindRemoteModule {
    private int type;
    private String uid;

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
}
