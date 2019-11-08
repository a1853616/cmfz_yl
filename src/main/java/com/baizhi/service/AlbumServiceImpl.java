package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.StartDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Start;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private StartDao startDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Album album=new Album();
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        Map<String,Object> map=new HashMap<>();
        //总条数
        int count=albumDao.selectCount(album);
        List<Album> list=albumDao.selectByRowBounds(album,rowBounds);
        for (Album a : list) {
            Start start = startDao.selectByPrimaryKey(a.getAuthor());
            a.setStart(start);
        }
        map.put("rows",list);
        map.put("page",page);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("count",count);
        return map;
    }

    @Override
    //添加
    public String add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCreat_date((new Date()));
        album.setCount("0");
        int i=albumDao.insertSelective(album);
        if(i==0){
            throw  new RuntimeException("添加失败");
        }
        return album.getId();
    }

    @Override
    public void edit(Album album) {
        int i = albumDao.updateByPrimaryKeySelective(album);
        if(i==0){
            throw new RuntimeException("修改专辑失败");
        }
    }

    @Override
    public Album findOne(String id) {
        Album album=albumDao.selectByPrimaryKey(id);
        return album;
    }
    //edit


}
