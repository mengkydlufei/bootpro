package com.cgq.vo;

import java.io.Serializable;

public class ChartVo implements Serializable {
    private String info;
    private String userid;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
