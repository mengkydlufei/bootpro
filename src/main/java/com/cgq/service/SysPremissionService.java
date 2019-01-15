package com.cgq.service;

import com.cgq.pojo.SysPremission;

import java.util.List;

/**
 * Created by 1 on 2018/10/9.
 */
public interface SysPremissionService {
    List<SysPremission> selectPermissionByRole(String rid);
}
