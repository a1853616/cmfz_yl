package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    private String id;
    private  String title;
    private  String cover;
    private String count;//总章数
    private String score;
    private  String author;
    private String brief;//简介
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creat_date;
    private List<Chapter> chapters;
    private Start start ;

}
