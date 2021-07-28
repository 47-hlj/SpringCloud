package cn.tedu.order.service.impl;

import cn.tedu.order.entity.Order;
import cn.tedu.order.mapper.OrderMapper;
import cn.tedu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 47HLJ
 * @date 2021/7/28 21:33
 * @description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void create(Order order) {
        //TODO 调用发号器获取订单id

        //临时产生订单id，调用发号器后这里代码删除
        long orderId=Math.abs(new Random().nextLong());
        order.setId(orderId);

        orderMapper.create(order);
        // 减少库存

        // 扣减账户
    }
}
