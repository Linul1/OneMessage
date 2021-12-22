package com.luckytain.onemessage.onemessage.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckytain.onemessage.onemessage.Mapper.SentenceMapper;
import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper, sentence> implements SentenceService {

    @Autowired
    SentenceService sentenceService;

    @Override
    public sentence getRandomOneByType(String type) {
        try {
            QueryWrapper<sentence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", type);
            return sentenceService.getOne(queryWrapper.last("ORDER BY RAND() LIMIT 0,1"));
        }
        catch (Exception e){
//            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean addOne(sentence s) {
        if (
                s.getSentence() == null || s.getType() == null || s.getProvenance() == null || s.getAuthor() == null ||
                s.getSentence().equalsIgnoreCase("") || s.getProvenance().equalsIgnoreCase("") ||
                s.getAuthor().equalsIgnoreCase("") || s.getType().equalsIgnoreCase("")
        ){
            return false;
        }
        s.setId(0);
        s.setLength(s.getSentence().length());
        s.setMark(0);
        s.setUploader_uid(1);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s.setUploadtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        s.setCreatedtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        return sentenceService.save(s);
    }


    @Override
    public boolean updateOne(sentence s) {
        if (s.getId() == 0){
            return false;
        }
        sentence updated;
        try {
            updated = sentenceService.getById(s.getId());
        }
        catch (Exception e){
//            System.out.println(e);
            return false;
        }
        if (updated == null){
            return false;
        }
        if (!s.getSentence().equalsIgnoreCase("") && s.getSentence() != null){ // 用户提交的语句是有内容的
            updated.setSentence(s.getSentence());
            updated.setLength(s.getSentence().length());
        }
        if (!s.getType().equalsIgnoreCase("") && s.getType() != null){ // 用户提交的类型是有内容的
            updated.setType(s.getType());
        }
        if (!s.getProvenance().equalsIgnoreCase("") && s.getProvenance() != null){
            updated.setProvenance(s.getProvenance());
        }
        if (!s.getAuthor().equalsIgnoreCase("") && s.getAuthor() != null){
            updated.setAuthor(s.getAuthor());
        }
        return sentenceService.updateById(updated);
    }

    @Override
    public List<Object> getallType() {
        QueryWrapper<sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("type").groupBy("type");
        return sentenceService.listObjs(queryWrapper);
    }

    @Override
    public sentence getRandomOne() {
        try {
            QueryWrapper<sentence> queryWrapper = new QueryWrapper<>();
            return sentenceService.getOne(queryWrapper.last("ORDER BY RAND() LIMIT 0,1"));
        }
        catch (Exception e){
            return null;
        }
    }
}
