package xyz.jzab.common.domain;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author JZAB
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T>{
    private Long total;
    private Long pages;
    private List<T> list;

    public static <T> PageDTO<T> of(Page<T> page){
        return new PageDTO<>(page.getTotal(),page.getPages(),page.getRecords());
    }

    public static <T,R> PageDTO<R> of(Page<T> page,Class<R> rClass){
        return new PageDTO<>(
            page.getTotal(),
            page.getPages(),
            BeanUtil.copyToList(page.getRecords(),rClass)
        );
    }

    public static <T,R> PageDTO<R> of(Page<T> page, Function<T,R> function){
        return new PageDTO<>(
            page.getTotal(),
            page.getPages(),
            page.getRecords()
                .stream()
                .map(function)
                .collect(Collectors.toList())
        );
    }
}
