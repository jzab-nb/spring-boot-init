package xyz.jzab.common.domain;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author JZAB
 */
@Data
public class PageRequest {
    // 页码
    private Integer pageNo = 1;
    // 页大小
    private Integer pageSize = 20;
    // 排序
    private List<OrderItem> orderBy = ListUtil.empty( );

    public <T> Page<T> toPageEntity(OrderItem... orderItems) {
        Page<T> page = Page.of(pageNo, pageSize);
        // 添加前端传来的排序字段
        if (orderBy != null && !orderBy.isEmpty( )) {
            page.addOrder(orderBy);
        }
        // 添加后端指定的排序字段
        if (orderItems != null && orderItems.length > 0) {
            page.addOrder(orderItems);
        }
        return page;
    }

    public <T> Page<T> toPageEntity(String orderBy, boolean asc) {
        return toPageEntity(new OrderItem(orderBy, asc));
    }

    public <T> Page<T> toDefaultPageEntity(){
        return toPageEntity("create_time",false);
    }
}
