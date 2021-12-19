package com.luckytain.onemessage.onemessage.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class sentence {
    private int id;
    private String sentence;
    private String type;
    private String provenance;
    private String author;
    private int uploader_uid;
    private Timestamp upload_time;
    private Timestamp created_time;
    private int length;
    private int mark;
}
