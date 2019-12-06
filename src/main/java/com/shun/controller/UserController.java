package com.shun.controller;

import com.shun.entity.User;
import com.shun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public String register(User user, String vcode, HttpServletRequest request) throws UnsupportedEncodingException {
        String code = (String) request.getSession().getAttribute("code");
        if (!code.equals(vcode)) return "redirect:/user/regist.jsp?errorMsg=" + URLEncoder.encode("验证码错误！", "UTF-8");

        Boolean r = userService.register(user);
        if (r) {
            System.out.println("注册成功！");
            return "redirect:/user/login.jsp";
        } else {
            System.out.println("注册失败！");
            return "redirect:/user/regist.jsp?errorMsg=" + URLEncoder.encode("注册失败！请正确输入数据！", "UTF-8");
        }
    }

    @RequestMapping("login")
    public String login(User user, HttpServletRequest request) throws UnsupportedEncodingException {
        User reUser = userService.login(user);
        if (reUser == null) {
            System.out.println("登录失败！");
            return "redirect:/user/login.jsp?errorMsg=" + URLEncoder.encode("用户名或密码错误！", "UTF-8");
        } else {
            System.out.println("登录成功！");
            request.getSession().setAttribute("user", reUser);
            return "redirect:/dept/getAll";
        }
    }
}
