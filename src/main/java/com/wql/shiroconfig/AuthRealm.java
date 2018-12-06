package com.wql.shiroconfig;


import com.wql.model.Permission;
import com.wql.model.Role;
import com.wql.model.User;
import com.wql.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCX on 2018/12/5.
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权，访问需要根据角色或权限访问的页面时进行授权认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从session中取出user
        User user= (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<Role> roles=user.getRoles();
        List<String> roleList=new ArrayList<>();
        List<String> permissions=new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                roleList.add(role.getName());
                List<Permission> permissionList=role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionList)){
                    for (Permission permission : permissionList) {
                        permissions.add(permission.getName());
                    }
                }
            }
        }
        //定义AuthorizationInfo的简单实现类
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //实现中有根据Permission对象和String添加权限的方法，此处使用权限名集合做添加
        info.addStringPermissions(permissions);
        info.addRoles(roleList);
        return info;
    }

    //认证登陆,登陆成功之后，把登陆对象放入session中。然后进行授权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登陆信息转换为UsernamePasswordToken
        UsernamePasswordToken passwordToken= (UsernamePasswordToken) authenticationToken;
        //取出登陆的用户名，查出user对象
        String userName=passwordToken.getUsername();
        User user=userService.findByName(userName);
        return new SimpleAuthenticationInfo(user,user.getPswd(),this.getClass().getName());
    }
}
