package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;
    //查所有
    @RequestMapping("findAll")
    public Map<String,Object>  findAll(Integer page, Integer rows,String albumId){
        Map<String,Object> map=chapterService.findAll(page, rows,albumId);
        return map;
    }
    //添加
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Chapter chapter){
        Map<String,Object> map=new HashMap<>();
        try {
            if("add".equals(oper)){
                String id=chapterService.add(chapter);
                map.put("message",id);
                map.put("status",true);
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return  map;
    }
    //文件上传
    @RequestMapping("upload")
    public  Map<String,Object> uplload(MultipartFile title,String id,String albumId,HttpServletRequest request){
       Map<String,Object> map=new HashMap<>();
       try {
           //文件上传
           File file=new File(request.getServletContext().getRealPath("albumAndchapter/files"),title.getOriginalFilename());
           title.transferTo(file);
           //修改文件名
           Chapter chapter=new Chapter();
           chapter.setId(id);
           chapter.setTitle(title.getOriginalFilename());
           //文件大小
           BigDecimal size=new BigDecimal(title.getSize());
           BigDecimal mod=new BigDecimal(1024);
           BigDecimal realSize=size.divide(mod).divide(mod).setScale(2,BigDecimal.ROUND_HALF_UP);
           chapter.setSize(realSize+"MB");

           Encoder encoder=new Encoder();
           long duration=encoder.getInfo(file).getDuration();
           chapter.setDuration(duration/1000/60+":"+duration/1000%60);
           chapterService.edit(chapter);
           //修改专辑中的数量
           Album album=albumService.findOne(albumId);
           album.setCount(album.getCount()+1);
           albumService.edit(album);
           map.put("status",true);
       }catch (Exception e){
           e.printStackTrace();
           map.put("status",false);
       }
       return  map;
    }

}
