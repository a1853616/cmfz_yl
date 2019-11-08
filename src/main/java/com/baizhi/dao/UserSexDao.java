package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserSexDao {
    //查询所有男生人数
    List<User> findAllSexAndMonth(String sex);
}
