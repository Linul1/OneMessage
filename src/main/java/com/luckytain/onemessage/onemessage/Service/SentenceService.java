package com.luckytain.onemessage.onemessage.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luckytain.onemessage.onemessage.entity.sentence;

public interface SentenceService extends IService<sentence> {
    sentence getRandomOneByType(String type);
    boolean addOne(sentence s);
    boolean updateOne(sentence s);
}
