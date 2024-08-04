package org.example.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.mybatis_plus.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Update("update user set age=age-#{age} where id = #{id}")
    void deductAge(Long id, Integer age);
    @Select("select * from user where name like  CONCAT('%', #{name}, '%') and age=#{age}")
    List<User> getByName(String name, Long age);
}
