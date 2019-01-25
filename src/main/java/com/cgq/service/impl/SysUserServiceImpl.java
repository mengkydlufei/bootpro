package com.cgq.service.impl;

import com.cgq.mapper.SysUserMapper;
import com.cgq.pojo.SysUser;
import com.cgq.service.SysUserService;
import com.cgq.utils.ParamDto;
import com.cgq.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1 on 2018/9/19.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisClient redisClient;
    @Override
    public List queryData(ParamDto paramDto) {
        List list = sysUserMapper.queryData(paramDto);
        redisClient.setObjectList("查询用户",list,10000);
//        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
//        operations.set("yyy",list);
//        System.out.println(operations);
        return list;
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

    @Override
    public void insertUser(SysUser sysUser) {
        sysUserMapper.insertUser(sysUser);
    }
}
