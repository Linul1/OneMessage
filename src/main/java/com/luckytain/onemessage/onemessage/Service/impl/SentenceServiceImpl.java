package com.luckytain.onemessage.onemessage.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckytain.onemessage.onemessage.Controller.utils.R;
import com.luckytain.onemessage.onemessage.Mapper.SentenceMapper;
import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper, sentence> implements SentenceService {

    @Autowired
    SentenceService sentenceService;

    @Override
    public sentence getRandomOneByType(String type) {
        try {
            QueryWrapper<sentence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", type);
            sentence s = sentenceService.getOne(queryWrapper.last("ORDER BY RAND() LIMIT 0,1"));
            return s;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean addOne(sentence s) {
        if (s.getSentence() == null || s.getType() == null || s.getProvenance() == null || s.getAuthor() == null){
            return false;
        }
        s.setLength(s.getSentence().length());
        s.setMark(0);
        s.setUploader_uid(1);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s.setUploadtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        s.setCreatedtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        if(sentenceService.save(s)){
            return true;
        }
        return false;
    }


    @Override
    public boolean updateOne(sentence s) {
        if (s.getId() == 0){
            return false;
        }
        sentence updated = sentenceService.getById(s.getId());
        return true;
    }
}
