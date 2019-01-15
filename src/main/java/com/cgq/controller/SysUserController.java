package com.cgq.controller;

import com.cgq.service.SysUserService;
import com.cgq.utils.ParamDto;
import com.cgq.utils.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 2018/9/19.
 */

@RestController

public class SysUserController {
    private Logger logger= LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("queryData")
    @ApiOperation("查询用户")
    public ResultDto queryData(@RequestBody ParamDto paramDto){
        ResultDto resultDto=new ResultDto();
        List list=sysUserService.queryData(paramDto);
        logger.info("----------------------------");
        return resultDto.success(list);

    }

    @PostMapping("queryUserByUsername")
    @ApiOperation("查询用户根据用户名")
    public ResultDto queryUserByUsername(@RequestBody ParamDto paramDto){

        List list=sysUserService.queryUserByUsername(paramDto);
        logger.info("-----------username------------");
        return new ResultDto().success(list);
    }
}
