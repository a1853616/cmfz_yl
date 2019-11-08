package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Start;
import com.baizhi.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("start")
public class StartController {
    @Autowired
    private StartService startService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object>  findAll(Integer page,Integer rows){
        Map<String,Object> map=startService.findAll(page, rows);
        System.out.println("xxxxxxxxxxxxxx"+map);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper, Start start){
        System.out.println("start:"+start);
        Map<String,Object> map=new HashMap<>();
        try{
            if ("add".equals(oper)){
                String id=startService.add(start);
                map.put("message",id);
            }
            map.put("status",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    //上传
    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile photo, String id, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        try {
            //  文件上传
            photo.transferTo(new File(request.getServletContext().getRealPath("/starAnduser/img"),photo.getOriginalFilename()));
//            修改start对象中cover属性
           Start start=new Start();
            start.setId(id);
            start.setPhoto(photo.getOriginalFilename());
            startService.edit(start);
            map.put("status",true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }
    //查所有
    @RequestMapping("getAllStarForSelect")
    public void getAllStarForSelect(HttpServletResponse response) throws IOException {
        List<Start> list = startService.startAll();
        String str = "<select>";
        for (Start star : list) {
            str += "<option value=" + star.getId() + ">" + star.getNickname() + "</option>";
        }
        str += "</select>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(str);
    }

}
