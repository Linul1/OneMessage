package com.luckytain.onemessage.onemessage.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckytain.onemessage.onemessage.Mapper.SentenceMapper;
import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper, sentence> implements SentenceService {

    @Override
    public sentence getSentenceByID(int id) {
        return null;
    }

}
