package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {
   @Autowired
    private BannerDao bannerDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object>findAll(Integer page,Integer rows) {
       Banner banner1=new Banner();
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
       Map<String,Object> map=new HashMap<>();
       int count=bannerDao.selectCount(banner1);
       List<Banner> list= bannerDao.selectByRowBounds(banner1,rowBounds);
       map.put("rows",list);
       map.put("page",page);
       //一共多少页
       map.put("total",count%rows==0?count/rows:count/rows+1);//
        //总共多少条
        map.put("records",count);
        return map;
    }

    @Override
    //添加
    public String add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setCreate_date((new Date()));
        int i=bannerDao.insertSelective(banner);
        if(i==0){
            throw  new RuntimeException("添加失败");
        }
        return banner.getId();
    }

    @Override
    //修改
    public void edit(Banner banner) {
        if("".equals(banner.getCover())){
            banner.setCover(null);
        }
        try {
            bannerDao.updateByPrimaryKeySelective(banner);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("修改失败");
        }
    }
//删除
    @Override
    public void del(String id, HttpServletRequest request) {
        Banner banner=bannerDao.selectByPrimaryKey(id);
        int i=bannerDao.deleteByPrimaryKey(id);
        if(i==0){
            throw new RuntimeException("删除失败");
        }else {
            String cover=banner.getCover() ;
            File file=new File((request.getServletContext().getRealPath("/banner/imger/")),cover);
            boolean b=file.delete();
            if (b==false){
                throw  new  RuntimeException("删除轮播图失败");
            }
        }
    }


}
