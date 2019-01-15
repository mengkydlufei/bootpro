package com.cgq.service.impl;

import com.cgq.mapper.SysUserMapper;
import com.cgq.pojo.SysUser;
import com.cgq.service.SysUserService;
import com.cgq.utils.ParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1 on 2018/9/19.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List queryData(ParamDto paramDto) {
        return sysUserMapper.queryData(paramDto);
    }

    @Override
    public List queryUserByUsername(ParamDto paramDto) {
        return sysUserMapper.queryUserByUsername(paramDto);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public SysUser selectUserByNameAndPwd(ParamDto paramDto) {
        return sysUserMapper.selectUserByNameAndPwd(paramDto);
    }
}
