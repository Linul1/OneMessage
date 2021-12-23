package com.luckytain.onemessage.onemessage.Controller;

import cn.dev33.satoken.stp.StpUtil;
import com.luckytain.onemessage.onemessage.Controller.utils.R;
import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;

    @RequestMapping("/getall")
    public R getall(){
        if (!StpUtil.isLogin()){
            return new R(false, "你没有权限");
        }
        return new R(true, sentenceService.list());
    }

    @RequestMapping("/getbyid")
    public R getOneByID(int id){
        if (!StpUtil.isLogin()){
            return new R(false, "你没有权限");
        }
        try {
            sentence s = sentenceService.getById(id);
            if (s == null){
                return new R(false, "没有这个ID");
            }
            return new R(true, s);
        }
        catch (Exception e){
            return new R(false);
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

    @RequestMapping("/getone")
    public R getone(){
        sentence s = sentenceService.getRandomOne();
        if (s != null){
            return new R(true, s);
        }
        else {
            return new R(false, "错误");
        }
    }

    @RequestMapping("/add")
    public R addone(@RequestBody sentence s){
        if (!StpUtil.isLogin()){
            return new R(false, "你没有权限");
        }
        if (sentenceService.addOne(s)){
            return new R(true, s);
        }
        else {
            return new R(false, "错误");
        }
    }

    @RequestMapping("/update")
    public R updateone(@RequestBody sentence s){
        if (!StpUtil.isLogin()){
            return new R(false, "你没有权限");
        }
        if (sentenceService.updateOne(s)){
            return new R(true, "修改成功");
        }
        else {
            return new R(false, "修改失败");
        }

    }

    @RequestMapping("/delete")
    public R deleteoneById(String id){
        if (!StpUtil.isLogin()){
            return new R(false, "你没有权限");
        }
        if (sentenceService.removeById(id)){
            return new R(true, "删除成功");
        }
        else{
            return new R(false, "删除失败");
        }
    }

    @RequestMapping("/getalltype")
    public R getAllType(){
        List<Object> l = sentenceService.getallType();
        if (l != null){
            return new R(true, l);
        }
        else {
            return new R(false);
        }
    }
}
