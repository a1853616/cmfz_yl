package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.dao.UserSexDao;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
        @Autowired
        private UserDao userDao;
        @Autowired
        private UserSexDao userSexDao;
        @Override
        @Transactional(propagation = Propagation.SUPPORTS)
        public Map<String, Object> findAll(Integer page, Integer rows,String startId) {
            User user=new User();
            user.setStartId(startId);
            RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
            Map<String,Object> map=new HashMap<>();
            //技术算数量
            int count=userDao.selectCount(user);
            List<User> list=userDao.selectByRowBounds(user,rowBounds);
            System.out.println("!!!!!!!!!!!!!!!"+list);
            map.put("rows",list);
            map.put("page",page);
            //共多少页
            map.put("total",count%rows==0?count/rows:count/rows+1);
            //一共多少条
            map.put("recods",count);
            return map;
        }

    public List<User> export() {
        return userDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Integer> findAllNan(String sex) {
            List<Integer> list= Arrays.asList(0,0,0,0,0,0,0,0,0,0,0);
            List<User> sexlist=userSexDao.findAllSexAndMonth(sex);
        System.out.println(sexlist);
            for (int i=0;i<sexlist.size();i++){
                Integer month=sexlist.get(i).getMonth();
                for (int j = 0; j < 12; j++) {
                    if(month==j+1){
                        list.set(j,sexlist.get(i).getCount());
                    }
                }

            }
        return list;
    }


}
