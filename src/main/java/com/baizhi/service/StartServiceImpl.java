package com.baizhi.service;

import com.baizhi.dao.StartDao;
import com.baizhi.entity.Start;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("startService")
@Transactional
public class StartServiceImpl implements StartService {
    @Autowired
    private StartDao startDao;
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Start start =new Start();
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        Map<String,Object> map= new HashMap<>();
        //总条数
        int count=startDao.selectCount(start);
        List<Start> list=startDao.selectByRowBounds(start,rowBounds);
        map.put("rows",list);
        map.put("page",page);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("count",count);

        return map;
    }

    @Override
    public String add(Start start) {
        start.setId(UUID.randomUUID().toString());
        int i=startDao.insertSelective(start);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return start.getId();
    }

    @Override
    public void edit(Start start) {
        if("".equals(start.getPhoto())){
            start.setPhoto(null);
        }
        try {
           startDao.updateByPrimaryKeySelective(start);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("修改失败");
        }
    }

    @Override
    public List<Start> startAll() {
        List<Start> list=startDao.selectAll();
        return list;
    }




}
