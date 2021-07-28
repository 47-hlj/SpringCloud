package cn.tedu.order.mapper;

import cn.tedu.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 47HLJ
 * @date 2021/7/28 21:31
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    void create(Order order);
}
