package com.cgq.aop;

import com.alibaba.fastjson.JSON;
import com.cgq.mapper.OperateLogMapper;
import com.cgq.pojo.OperateLogDto;
import com.cgq.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
/**
 *@author cgq
 *@date 2019/1/16
 *@Param
 *@return
 *@description 日志
 */
@Aspect
@Component
public class SysLogAop {
    @Autowired
    private OperateLogMapper operateLogMapper;

    ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    @Pointcut("@annotation(com.cgq.annotation.PointDemo)")
    public void pointAspect1(){

    }
    @Pointcut("execution(* com.cgq.controller.*.query*(..)) || execution(* com.cgq.controller.*.insert*(..)) "
            + "|| execution(* com.cgq.controller.*.update*(..)) || execution(* com.cgq.controller.*.delete*(..))")
    public void pointAspect2(){

    }

    @Before("pointAspect1()")
    public void before(JoinPoint joinPoint){
        Date before = new Date();
        threadLocal.set(before.getTime());
    }

    @After("pointAspect1()")
    public void after(JoinPoint joinPoint){
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        String userid = sysUser.getId();
        ArrayList<Object> paramsArray = getArgs(joinPoint);
        String methodParams = JSON.toJSONString(paramsArray);

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        String methodFullPath = className + "." + methodName;
        String operate = null;
        if(methodName.contains("query")){
            operate = "查询";
        }else if(methodName.contains("insert")){
            operate = "新增";
        }else if(methodName.contains("update")){
            operate = "更新";
        }else if(methodName.contains("delete")){
            operate = "删除";
        }else {
            operate = "其他操作";
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        OperateLogDto operateLogDto = new OperateLogDto(
                userid,
                request.getRemoteAddr(),
                methodFullPath, operate,
                JSON.toJSONString(request.getParameterMap()), methodParams, "");

        operateLogMapper.insert(operateLogDto);

    }


    @Before("pointAspect2()")
    public void before2(JoinPoint joinPoint){
        Date before = new Date();
        threadLocal.set(before.getTime());
    }

    @After("pointAspect2()")
    public void after2(JoinPoint joinPoint){
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        String userid = sysUser.getId();
        ArrayList<Object> paramsArray = getArgs(joinPoint);
        String methodParams = JSON.toJSONString(paramsArray);

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        String methodFullPath = className + "." + methodName;
        String operate = null;
        if(methodName.contains("query")){
            operate = "查询";
        }else if(methodName.contains("insert")){
            operate = "新增";
        }else if(methodName.contains("update")){
            operate = "更新";
        }else if(methodName.contains("delete")){
            operate = "删除";
        }else {
            operate = "其他操作";
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        OperateLogDto operateLogDto = new OperateLogDto(
                userid,
                request.getRemoteAddr(),
                methodFullPath, operate,
                JSON.toJSONString(request.getParameterMap()), methodParams, "");

        operateLogMapper.insert(operateLogDto);

    }

    private ArrayList<Object> getArgs(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        ArrayList<Object> list = new ArrayList<>();
        for(Object o:args){
            if (o.getClass().getName().startsWith("org.springframework")) {
                continue;
            }
            list.add(o);
        }
        return list;
    }
}
