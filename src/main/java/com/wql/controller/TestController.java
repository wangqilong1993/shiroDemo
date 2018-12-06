package com.wql.controller;

import com.wql.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by PCX on 2018/12/5.
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //验证根据角色名访问
    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "success admin!!";
    }

    //登陆验证
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //会调用AuthRealm相关方法认证
            subject.login(token);
            User user = (User) subject.getPrincipal();
            //登陆成功，用户放入session中。可在authRealm认证时拿到Session中保存的用户信息
            session.setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }
}
