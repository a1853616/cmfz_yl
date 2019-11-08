package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Start;

import java.util.List;
import java.util.Map;

public interface StartService {
    //查所有
    Map<String,Object> findAll(Integer page,Integer rows);
    //添加
    String add(Start start);
    //edit
    void  edit(Start start);
    //查所有
    List<Start> startAll();

}
