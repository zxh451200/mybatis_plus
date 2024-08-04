package org.example.mybatis_plus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.example.mybatis_plus.enums.UserStatus;

import java.time.LocalDateTime;

@Data
@TableName(value = "user",autoResultMap = true)
public class User {
    private Long  id;
    private String name;
    private Integer age;
    private UserStatus status;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;
}
