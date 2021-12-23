package com.luckytain.onemessage.onemessage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class sentence {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String sentence;
    private String type;
    private String provenance;
    private String author;
    private int uploader_uid; // mp默认开了下划线转驼峰... 搞半天我以为我写错了
    private Timestamp uploadtime;
    private Timestamp createdtime;
    private int length;
    private int mark;
}
