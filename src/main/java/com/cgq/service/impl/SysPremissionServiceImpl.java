package com.cgq.service.impl;

import com.cgq.mapper.SysPremissionMapper;
import com.cgq.pojo.SysPremission;
import com.cgq.service.SysPremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1 on 2018/10/9.
 */
@Service
public class SysPremissionServiceImpl implements SysPremissionService {
    @Autowired
    private SysPremissionMapper sysPremissionMapper;


    @Override
    public List<SysPremission> selectPermissionByRole(String rid) {
        return sysPremissionMapper.selectPermissionByRole(rid);
    }
}
