package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public void login(Admin admin, String code, HttpServletRequest request) {
        HttpSession session=request.getSession();
        String code1=(String)session.getAttribute("code");
        if(code1.equalsIgnoreCase(code)){
            Admin admin1=adminDao.selectOne(admin);
            if(admin1!=null){
                session.setAttribute("admin1",admin1);
            }else {
               throw  new  RuntimeException("用户名或者密码错误");
            }
        } else {
               throw  new RuntimeException("验证码错误");
        }
    }
}
