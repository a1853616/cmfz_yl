package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows ,String startId){
        return articleService.findAll(page, rows,startId);
    }
    //上传
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile articleImg, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        File file=new File(request.getServletContext().getRealPath("article/img"),articleImg.getOriginalFilename());
        try{
            articleImg.transferTo(file);
            map.put("error",0);
            map.put("url","http://localhost:8989/cmfz/article/img/"+articleImg.getOriginalFilename());

        }catch (Exception e){
            e.printStackTrace();
            map.put("error",1);
        }
        return  map;
    }
    @RequestMapping("browse")
    public Map<String,Object> browse(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("article/img"));
        File[] files = file.listFiles();
        List<Object> list = new ArrayList<>();
        for (File img : files) {
            Map<String, Object> imgObject = new HashMap<>();
            imgObject.put("is_dir",false);
            imgObject.put("has_file",false);
            imgObject.put("filesize",img.length());
            imgObject.put("is_photo",true);
            imgObject.put("filetype", FilenameUtils.getExtension(img.getName()));
            imgObject.put("filename",img.getName());
            imgObject.put("datetime","2018-06-06 00:36:39");
            list.add(imgObject);
        }
        map.put("file_list",list);
        map.put("total_count",list.size());
        map.put("current_url","http://localhost:8989/cmfz/article/img/");
        return map;
    }
    @RequestMapping("add")
    public String add(Article article){
            articleService.add(article);
        return "redirect:findAll";
    }
    @RequestMapping("edit")
    public String edit(Article article){
            articleService.edit(article);
        return "redirect:findAll";
    }
    @RequestMapping("del")
    public String del(String id){
            articleService.del(id);
        return "redirect:findAll";
    }
}
