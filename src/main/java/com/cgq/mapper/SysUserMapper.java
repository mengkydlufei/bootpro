package com.cgq.mapper;

import com.cgq.pojo.SysUser;
import com.cgq.utils.ParamDto;

import java.util.List;

public interface SysUserMapper {
    SysUser selectByUsername(String username);

    List queryData(ParamDto paramDto);

    List queryUserByUsername(ParamDto paramDto);

    SysUser selectUserByNameAndPwd(ParamDto paramDto);
}