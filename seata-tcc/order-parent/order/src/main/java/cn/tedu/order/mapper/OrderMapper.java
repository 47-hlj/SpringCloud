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
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

    /**
     * 更新订单状态
     * @param id
     * @param status
     */
    void updateStatus(Long id,Integer status);

    /**
     * 删除订单
     * @param id
     */
//    void deleteById(Long id); 使用继承的deleteById


}
