package com.cgq.common.shiro.realm;

import com.cgq.pojo.SysPremission;
import com.cgq.pojo.SysRole;
import com.cgq.pojo.SysUser;
import com.cgq.service.SysPremissionService;
import com.cgq.service.SysRoleService;
import com.cgq.service.SysUserService;
import com.cgq.utils.Encodes;
import com.cgq.utils.ParamDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 1 on 2018/9/20.
 */
public class MyShiroRealm  extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPremissionService sysPremissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser= (SysUser) principalCollection.getPrimaryPrincipal();

        List<SysRole> roles=  sysRoleService.selectRoleByUid(sysUser.getId());

        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        for (SysRole role:roles) {
            authorizationInfo.addRole(role.toString());
            for (SysPremission premission:sysPremissionService.selectPermissionByRole(role.getId())) {
                authorizationInfo.addStringPermission(premission.toString());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println(username+"-----------"+password);
        ParamDto paramDto=new ParamDto();
        paramDto.setUsername(username);
        paramDto.setPassword(password);
        SysUser user=sysUserService.selectByUsername(username);
        System.out.println(user);
        if(user==null) throw new UnknownAccountException();
        byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,
                user.getPassword().substring(16),
                ByteSource.Util.bytes(salt),
                getName()
        );
        Session session= SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession",user);
        session.setAttribute("userSessionId",user.getId());
        return authenticationInfo;
    }
}
