package com.cgq.utils;

import java.io.Serializable;

/**
 * Created by 1 on 2018/9/19.
 */
public class ResultDto implements Serializable {
    private Object data;
    private String msg;
    private Integer code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultDto success(Object data){
        this.code=200;
        this.msg="ok";
        this.data=data;
        return this;
    }

    public ResultDto faile(){
        this.code=200;
        this.msg="密码或账户不能为空";
        return this;
    }
}
