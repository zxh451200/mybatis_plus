package org.example.mybatis_plus.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Tag(name = "分页结果")
public class PageDto<T> {
    @Parameter(name = "total", description = "总数据数")
    private Long total;
    @Parameter(name = "pages", description = "总页数")
    private Long pages;
    @Parameter(name = "list", description = "数据")
    private List<T> list;


    public static <PO, VO> PageDto<VO> of(Page<PO> p, Class<VO> clazz) {
        PageDto<VO> dto = new PageDto<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if (CollUtil.isEmpty(records)) {
            dto.setList(Collections.emptyList());
        }
        dto.setList(BeanUtil.copyToList(records, clazz));
        return dto;
    }

    public static <PO, VO> PageDto<VO> of1(Page<PO> p, Function<PO, VO> f) {
        PageDto<VO> dto = new PageDto<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if (CollUtil.isEmpty(records)) {
            dto.setList(Collections.emptyList());
        }
        dto.setList(records.stream().map(f).collect(Collectors.toList()));
        return dto;
    }

}
