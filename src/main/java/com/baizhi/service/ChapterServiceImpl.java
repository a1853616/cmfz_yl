package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {
   @Autowired
    private ChapterDao chapterDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object>findAll(Integer page,Integer rows,String albumId) {
      Chapter chapter=new Chapter();
      chapter.setAlbumId(albumId);
      RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
       Map<String,Object> map=new HashMap<>();
       int count=chapterDao.selectCount(chapter);
       List<Chapter> list= chapterDao.selectByRowBounds(chapter,rowBounds);
       map.put("rows",list);
       map.put("page",page);
       //一共多少页
       map.put("total",count%rows==0?count/rows:count/rows+1);//
        //总共多少条
        map.put("records",count);
        return map;
    }

    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreat_date(new Date());
        int i=chapterDao.insertSelective(chapter);
        if(i==0){
            throw  new RuntimeException("添加失败");
        }
        return chapter.getId();
    }


    public void edit(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if(i == 0){
            throw new RuntimeException("修改章节失败");
        }
    }



/*
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
*/

}
