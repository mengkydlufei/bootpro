package com.cgq.service.impl;

import com.cgq.mapper.SysRoleMapper;
import com.cgq.pojo.SysRole;
import com.cgq.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1 on 2018/10/9.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> selectRoleByUid(String uid) {
        return sysRoleMapper.selectRoleByUid(uid);
    }
}
