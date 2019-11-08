package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    //查所有
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows,String startId){
        Map<String,Object> map=userService.findAll(page,rows,startId);
        System.out.println("ssssssssssssss"+map);
        return map;
    }
    @RequestMapping("export")
    public void export(HttpServletResponse resp){
//        准备数据
//        userService...
        List<User> list = userService.export();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"), com.baizhi.entity.User.class, list);

        String fileName = "用户报表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";
        //处理中文下载名乱码
        try {
            fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition","attachment;filename="+fileName);
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //earchars统计每月男女注册个数
    @RequestMapping("findCound")
    public Map<String,Object> findCount(){
        Map<String,Object> map=new HashMap<>();
        List<Integer> men = userService.findAllNan("男");
        List<Integer> women= userService.findAllNan("女");
        map.put("men",men);
        map.put("women",women);

        return map;
    }


}
