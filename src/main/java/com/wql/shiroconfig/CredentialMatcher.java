package com.wql.shiroconfig;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by PCX on 2018/12/5.
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    //自定义密码匹配规则
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String password= String.valueOf(usernamePasswordToken.getPassword());
        String dbPassword= (String) info.getCredentials();
        return this.equals(password,dbPassword);
    }
}
