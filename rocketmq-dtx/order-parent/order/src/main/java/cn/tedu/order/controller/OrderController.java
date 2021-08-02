package cn.tedu.order.controller;

import cn.tedu.order.entity.Order;
import cn.tedu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 47HLJ
 * @date 2021/7/28 21:34
 * @description
 */
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String create(Order order){
        orderService.create(order);
        return "创建订单成功";
    }
}
