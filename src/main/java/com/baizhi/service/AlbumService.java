package com.baizhi.service;

import com.baizhi.entity.Album;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AlbumService {
    //查所有
    Map<String,Object> findAll(Integer page,Integer rows);
    //添加
    String add(Album album);
    void edit(Album album);
    Album findOne(String id);
}
