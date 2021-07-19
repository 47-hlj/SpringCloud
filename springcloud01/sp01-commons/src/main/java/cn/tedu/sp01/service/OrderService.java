package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.Order;


/**
 * @author 47HLJ
 * @date 2021/7/19 11:34
 * @description
 */
public interface OrderService {
    /**
     * 获取订单
     * @param orderId
     * @return
     */
    Order getOrder(String orderId);

    /**
     * 添加订单
     * @param order
     */
    void addOrder(Order order);
}
