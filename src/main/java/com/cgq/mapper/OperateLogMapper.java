package com.cgq.mapper;


import com.cgq.pojo.OperateLogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    int insert(OperateLogDto record);

    int insertSelective(OperateLogDto record);

    OperateLogDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperateLogDto record);

    int updateByPrimaryKey(OperateLogDto record);
}