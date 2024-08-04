package org.example.mybatis_plus.vo;

import lombok.Data;
import org.example.mybatis_plus.enums.UserStatus;
import org.example.mybatis_plus.pojo.UserInfo;

@Data
public class UserVo {
    private Long  id;
    private String name;
    private Integer age;
    private UserStatus status;
    private UserInfo info;
}
