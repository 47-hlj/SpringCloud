package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 16:53
 * @description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单，orderId="+orderId);
        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("添加订单：order="+order);
    }
}
