package com.luckytain.onemessage.onemessage.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luckytain.onemessage.onemessage.entity.user;

public interface UserService extends IService<user> {
    user getByUsernameAndPassword(String username, String password);
    boolean register(String username, String password);
}
