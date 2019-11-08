package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查所有
    Map<String,Object> findAll(Integer page,Integer rows,String startId);
    List<User> export();
    List<Integer> findAllNan(String sex);


}
