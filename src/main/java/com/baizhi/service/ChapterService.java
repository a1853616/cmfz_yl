package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //查所有
    Map<String,Object> findAll(Integer page, Integer rows,String albumId);
    //添加
    String add(Chapter chapter);
    //edit
    void edit(Chapter chapter);
}
