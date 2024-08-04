package org.example.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.experimental.Accessors;
import org.example.mybatis_plus.mapper.UserMapper;
import org.example.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user=new User();
        user.setName("张三");
        user.setAge(18);

        userMapper.insert(user);
    }



    @Test
    void getList() {
        QueryWrapper<User> age = new QueryWrapper<User>().eq("age", 18);
        userMapper.selectList(age).forEach(System.out::println);
    }

    @Test
    void updateAge() {
        QueryWrapper<User> age = new QueryWrapper<User>().eq("age", 18);
        userMapper.selectList(age).forEach(System.out::println);
    }

}
