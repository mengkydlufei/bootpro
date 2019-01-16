package com.cgq.controller;

import com.cgq.annotation.PointDemo;
import com.cgq.service.SysUserService;
import com.cgq.utils.ParamDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 2018/10/9.
 */

@Controller
public class LoginController {



    @Autowired
    private SysUserService sysUserService;

    @GetMapping("login")
    public String login(HttpServletRequest request){
        return "login";
    }
//, HttpSession session, HttpServletRequest request

    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    public String tologin(ParamDto paramDto,HttpServletRequest request){

        if(StringUtils.isEmpty(paramDto.getUsername())||StringUtils.isEmpty(paramDto.getPassword())){

//            request.setAttribute("msg", "用户名或密码不能为空！");
            return "用户名或密码不能为空";
        }

        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(paramDto.getUsername(),paramDto.getPassword());
        System.out.println(token);

        try{
            subject.login(token);
//            session.setAttribute("user", user);
            return "index";
        }catch (LockedAccountException lae) {
            token.clear();
//            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
//            request.setAttribute("msg", "用户或密码不正确！");
            return "用户或密码不正确";
        }
    }
    /**
     * 退出登陆
     * @return
     */
    @PointDemo
    @ResponseBody
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //在这里执行退出系统前需要清空的数据
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "redirect:/login";
    }


}
