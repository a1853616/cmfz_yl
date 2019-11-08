package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
        @Autowired
        private ArticleDao articleDao;
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows,String startId) {
       Article article=new Article();
       article.setId(startId);
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        //总条数
        int count=articleDao.selectCount(article);
        List<Article> list=articleDao.selectByRowBounds(article,rowBounds);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("page",page);
        map.put("toatal",count%rows==0?count/rows:count/rows+1);
        map.put("count",count);
        return map;
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreatDate( new Date());
         articleDao.insertSelective(article);
    }

    @Override
    public void edit(Article article) {
    articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public void del(String id) {
        articleDao.deleteByPrimaryKey(id);
    }
}
