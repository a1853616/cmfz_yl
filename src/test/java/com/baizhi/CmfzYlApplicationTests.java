package com.baizhi;

import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmfzYlApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        List<Integer> men = userService.findAllNan("男");
        for (Integer man : men) {
            System.out.println(man);
        }
        List<Integer> women= userService.findAllNan("女");
    }

}
