package org.example.mybatis_plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mybatis_plus.dto.PageDto;
import org.example.mybatis_plus.pojo.User;
import org.example.mybatis_plus.query.UserQuery;
import org.example.mybatis_plus.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends IService<User> {


    void deductAge(Long id, Integer age) throws Exception;


    List<User> getByName(String name, Long age);

    PageDto<UserVo> getPageUserList(UserQuery userQuery);
}
