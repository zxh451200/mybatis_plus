package org.example.mybatis_plus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.mybatis_plus.dto.PageDto;
import org.example.mybatis_plus.dto.UserDto;
import org.example.mybatis_plus.pojo.Result;
import org.example.mybatis_plus.pojo.User;
import org.example.mybatis_plus.query.UserQuery;
import org.example.mybatis_plus.service.UserService;
import org.example.mybatis_plus.vo.UserVo;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户管理")
public class UserController {


    private final UserService userService;


    @Operation(summary = "插入用户")
    @PostMapping("insert")
    public void insertUser(@RequestBody UserDto userDto){
       //   User user = new User();
        User user = BeanUtil.copyProperties(userDto, User.class);
        userService.save(user);
    }


    @Operation(summary = "删除用户")
    @DeleteMapping("delete/{id}")
    public Result deleteUser(@Parameter(name="id",description = "用户id",required = true,in = ParameterIn.QUERY) @PathVariable("id") Long id){
        System.out.println(111111);
        userService.removeById(id);
        System.out.println(111111);
        return Result.success();
    }

     @Operation(summary = "修改用户")
     @PostMapping("update")
     public void updateUser(@RequestBody UserDto userDto){
         User user = BeanUtil.copyProperties(userDto, User.class);
         userService.updateById(user);
     }

     @Operation(summary = "获取分页用户列表")
     @GetMapping("/getPageUserList")
     public  Result<PageDto<UserVo>> getPageUserList(@ParameterObject UserQuery userQuery){
         System.out.println(1111111);
         System.out.println(userQuery);
         System.out.println(1111112);
         return Result.success(userService.getPageUserList(userQuery));
     }




     @Operation(summary = "根据id查询用户")
     @Parameter(name = "id", description = "用户的唯一标识 id", required = true, in = ParameterIn.QUERY)
//     @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json"))
     @GetMapping("getById/{id}")
     public Result<UserVo> selectUser(@PathVariable("id") Long id){
         User user = userService.getById(id);
         System.out.println(222222);
         System.out.println(user);
         System.out.println(111111);
         return Result.success(BeanUtil.copyProperties(user, UserVo.class));
     }

     @Operation(summary = "批量查询用户列表")
     @GetMapping
     public List<UserVo> selectUserList(@RequestParam("ids") List<Long> ids){
         List<User> userList = userService.listByIds(ids);
         return BeanUtil.copyToList(userList, UserVo.class);

     }

     @Operation(summary = "减年龄")
     @GetMapping("update/{id}/{age}")
     public void deductAge(@PathVariable("id") Long id,@PathVariable("age") Integer age) throws Exception {
         userService.deductAge(id,age);
     }

     @Operation(summary = "根据名字获得用户列表")
     @Parameter(name = "name", description = "用户名", in = ParameterIn.QUERY)
     @Parameter(name = "age", description = "年龄", in = ParameterIn.QUERY)
     @GetMapping("getByName/{name}/{age}")
     public Result<List<UserVo>> getByName(@PathVariable("name") String name, @PathVariable("age") Long age){
         List<User> user = userService.getByName(name,age);
         return Result.success(BeanUtil.copyToList(user, UserVo.class));
     }



}
