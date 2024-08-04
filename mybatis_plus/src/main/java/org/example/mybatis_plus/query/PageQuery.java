package org.example.mybatis_plus.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.mybatis_plus.pojo.User;

import java.io.Serializable;

@Data
@Schema(name = "分页户查询实体")
public class PageQuery {
    @Schema(name = "pageNo", description = "页码")
    private Integer pageNo;
    @Schema(name = "pageSize", description = "条数")
    private Integer pageSize;
    @Schema(name = "sortBy", description = "排序字段")
    private String sortBy;
    @Schema(name = "isAsc", description = "是否升序")
    private Boolean isAsc;


    public <T> Page<T> toMpPage(OrderItem... items) {
        Page<T> page = Page.of(pageNo, pageSize);
        if (StrUtil.isNotBlank(sortBy)) {
            if (isAsc) {
                page.addOrder(OrderItem.asc(sortBy));
            } else {
                page.addOrder(OrderItem.desc(sortBy));
            }
        } else if (items != null) {
            page.addOrder(items);
        } else {
            page.addOrder(OrderItem.desc("update_time"));
        }
        return page;
    }


    public <T> Page<T> defaultToMpPage() {
        return toMpPage(OrderItem.desc("update_time"));
    }
}
