package com.luckytain.onemessage.onemessage.Controller;

import com.luckytain.onemessage.onemessage.Controller.utils.R;
import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@RestController
@RequestMapping("/api")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;

    @RequestMapping("/getall")
    public R getall(){
        return new R(true, sentenceService.list());
    }

    @RequestMapping("/getbyid")
    public R getOneByID(int id){
        try {
            sentence s = sentenceService.getById(id);
            return new R(true, s);
        }
        catch (Exception e){
            System.out.println(e);
            return new R(false, "没有这个ID");
        }
    }

    @RequestMapping("/getonebytype")
    public R getoneByType(String type){
        sentence s = sentenceService.getRandomOneByType(type);
        if (s != null){
            return new R(true, s);
        }
        else {
            return new R(false, "错误");
        }
    }

    @RequestMapping("/add")
    public R addone(@RequestBody sentence s){
        if (sentenceService.addOne(s)){
            return new R(true, s);
        }
        else {
            return new R(false, "错误");
        }
    }

    @RequestMapping("/update")
    public R updateone(@RequestBody sentence s){
        return null;

    }
}
