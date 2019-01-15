package com.cgq.mapper;

import com.cgq.pojo.SysRole;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> selectRoleByUid(String uid);
}