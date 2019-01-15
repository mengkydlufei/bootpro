package com.cgq.mapper;

import com.cgq.pojo.SysPremission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysPremissionMapper {
        List<SysPremission> selectPermissionByRole(String rid);

}