package com.luckytain.onemessage.onemessage.Service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckytain.onemessage.onemessage.Mapper.UserMapper;
import com.luckytain.onemessage.onemessage.Service.UserService;
import com.luckytain.onemessage.onemessage.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, user> implements UserService {

    @Autowired
    UserService userService;

    @Override
    public user getByUsernameAndPassword(String username, String password) {
        Map<SFunction<user, ?>, Object> map = new HashMap<>();
        map.put(user::getUsername, username);
        map.put(user::getPassword, SaSecureUtil.md5(password));

        LambdaQueryWrapper<user> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.allEq(map);
        try {
            return userService.getOne(queryWrapper);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean register(String username, String password) {
        if (username == null || username.equalsIgnoreCase("") || password == null || password.equalsIgnoreCase("")){
            return false;
        }
        user u = new user();
        u.setUsername(username);
        u.setPassword(SaSecureUtil.md5(password));
        try {
            return userService.save(u);
        }
        catch (Exception e){
            return false;
        }

    }


}
