package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.Month;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "电话")
    private Integer phone;
    @Excel(name="用户名")
    private String username;
    @Excel(name="密码")
    private String password;
    @Excel(name="盐")
    private String sait;//盐
    @Excel(name="网名")
    private String yname;//网名
    @Excel(name="省份")
    private String province;
    @Excel(name="城市")
    private String city;
    @Excel(name="签名")
    private String sign;//签名
    @Excel(name="头像")
    private String headphoto;//头像
    @Excel(name="性别")
    private String sex;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="创建日期")
    private Date creat_date;//创建日期
    @Excel(name="明星编号")
    private String startId;
    private Integer  month;
    private Integer count;
}
