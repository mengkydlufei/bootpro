package com.cgq.utils;

import java.io.Serializable;

/**
 * Created by 1 on 2018/9/19.
 */
public class ParamDto implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String usrename) {
        this.username = usrename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
