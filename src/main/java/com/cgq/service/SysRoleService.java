package com.cgq.service;

import com.cgq.pojo.SysRole;

import java.util.List;

/**
 * Created by 1 on 2018/10/9.
 */
public interface SysRoleService {

    List<SysRole> selectRoleByUid(String uid);
}
