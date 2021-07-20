package cn.tedu.sp03.order.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author 47HLJ
 * @date 2021/7/19 17:00
 * @description
 */
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        Order order=orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }


    /**
     * 添加订单
     * @return
     */
    @GetMapping("/add")
    public JsonResult<?> addOrder(){
        Order order=new Order();
        order.setId("xlx");
        // 真实项目中，应该获取已登录的用户数据
        order.setUser(new User(8,null,null));
        order.setItems(Arrays.asList(new Item[]{
                new Item(1, "商品1", 3),
                new Item(2, "商品2", 2),
                new Item(3, "商品3", 5),
                new Item(4, "商品4", 1),
                new Item(4, "商品5", 2),
        }));
        orderService.addOrder(order);
        return JsonResult.ok().msg("订单添加成功");
    }


}
