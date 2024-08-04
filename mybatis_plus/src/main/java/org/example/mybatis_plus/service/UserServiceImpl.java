package org.example.mybatis_plus.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.example.mybatis_plus.dto.PageDto;
import org.example.mybatis_plus.mapper.UserMapper;
import org.example.mybatis_plus.pojo.User;
import org.example.mybatis_plus.query.UserQuery;
import org.example.mybatis_plus.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//继承ServiceImpl，实现UserService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public void deductAge(Long id, Integer age) throws Exception {


        User user = getById(id);
        if (user.getAge() <= 0) {
            throw new Exception("年龄不能小于0");
        }
        baseMapper.deductAge(id, age);

    }

    @Override
    public List<User> getByName(String name, Long age) {
//        return lambdaQuery()
//                .like(name!=null,User::getName,name)
//                .eq(age!=null,User::getAge,age)
//                .list();
        List<User> list = baseMapper.getByName(name, age);
        return list;
    }

    /*
    @Override
    public PageDto<UserVo> getPageUserList(UserQuery userQuery) {

        // 分页参数
        Page<User> page = Page.of(userQuery.getPageNo(),userQuery.getPageSize());
        // 排序参数, 通过OrderItem来指定
        page.addOrder(OrderItem.desc("id"));
        page.addOrder(OrderItem.desc("age"));
//        Page<User> userPage = page(page);
//        BeanUtil.copyToList(userPage.getRecords(), UserVo.class);
//        PageDto<UserVo> objectPageDto = new PageDto<>();
//


        Page<User> userPage = lambdaQuery()
                .like(userQuery.getName() != null, User::getName, userQuery.getName())
                .eq(userQuery.getAge() != null, User::getAge, userQuery.getAge())
//                .orderBy(true, userQuery.getIsAsc(), userQuery.getName())
//                .orderByAsc("age")
                .page(page);
        PageDto<UserVo> dto = new PageDto<>();
        dto.setTotal(page.getTotal());
        dto.setPages(page.getPages());
        List<User> records = userPage.getRecords();
        if (CollUtil.isEmpty(records)){
            dto.setList(Collections.emptyList());
            return dto;
        }
        dto.setList(BeanUtil.copyToList(records,UserVo.class));

        return dto;
    }

*/

//    @Override
//    public PageDto<UserVo> getPageUserList(UserQuery userQuery) {
//
//        Page<User> page = userQuery.toMpPage();
//        Page<User> userPage = lambdaQuery()
//                .like(userQuery.getName() != null, User::getName, userQuery.getName())
//                .eq(userQuery.getAge() != null, User::getAge, userQuery.getAge())
//                .page(page);
//
//        return PageDto.of(userPage, UserVo.class);
//    }

    @Override
    public PageDto<UserVo> getPageUserList(UserQuery userQuery) {

        System.out.println(33333333);

        Page<User> page = userQuery.toMpPage();
        Page<User> userPage = lambdaQuery()
                .like(userQuery.getName() != null, User::getName, userQuery.getName())
                .eq(userQuery.getAge() != null, User::getAge, userQuery.getAge())
                .page(page);

//        1.方式1
//        return PageDto.of1(userPage, user -> BeanUtil.copyProperties(user, UserVo.class));
//        方式2
        return PageDto.of1(userPage, user -> {
//            1.拷贝基本属性
            UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
//            2.处理特殊逻辑
            userVo.setName(userVo.getName().substring(0, userVo.getName().length() - 2) + "****");

            return userVo;
        });
    }
}
