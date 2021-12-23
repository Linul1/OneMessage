package com.luckytain.onemessage.onemessage.Controller;

import cn.dev33.satoken.stp.StpUtil;
import com.luckytain.onemessage.onemessage.Controller.utils.R;
import com.luckytain.onemessage.onemessage.Service.UserService;
import com.luckytain.onemessage.onemessage.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user/")
public class AuthController {

    @Autowired
    private UserService userService;

    // 登录
    @RequestMapping("login")
    public R doLogin(String username, String password) {
        user u = userService.getByUsernameAndPassword(username, password);
        if (u != null){
            StpUtil.login(u.getId());
            return new R(true, "登录成功");
        }
        return new R(false, "登录失败");
    }

    // 查询登录状态
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("getId")
    public String getID(){
        if (StpUtil.isLogin()){
            return "ID: " + StpUtil.getLoginId();
        }
        return "你还没登录";
    }

    // 注册
    @RequestMapping("register")
    public R register(String username, String password, String token){
        if (token.equals("TEMPTOKEN") && userService.register(username, password)){
            return new R(true, "注册成功");
        }
        return new R(false, "注册失败");
    }

    // 退出登录
    @RequestMapping("logout")
    public String quit(){
        StpUtil.logout();
        return "logout success";
    }
}
