package com.cgq.mapper;

import com.cgq.pojo.SysPermissionRole;

public interface SysPermissionRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermissionRole record);

    int insertSelective(SysPermissionRole record);

    SysPermissionRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermissionRole record);

    int updateByPrimaryKey(SysPermissionRole record);
}