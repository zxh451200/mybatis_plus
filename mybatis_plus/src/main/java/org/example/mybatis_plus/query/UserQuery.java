package org.example.mybatis_plus.query;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@ToString(callSuper = true)
@Schema(description = "用户查询实体")
public class UserQuery extends PageQuery{
    @Schema(name = "name",description = "名字")
    private String name;
    @Schema(name = "age",description = "年龄")
    private Integer age;

}
