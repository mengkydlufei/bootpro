package com.cgq.service;

import com.cgq.pojo.SysUser;
import com.cgq.utils.ParamDto;

import java.util.List;

/**
 * Created by 1 on 2018/9/19.
 */
public interface SysUserService {
    List queryData(ParamDto paramDto);
    List queryUserByUsername(ParamDto paramDto);
    SysUser selectByUsername(String username);

    SysUser selectUserByNameAndPwd(ParamDto paramDto);
    void insertUser(SysUser sysUser);
}
