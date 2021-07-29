package cn.tedu.order.service.impl;

import cn.tedu.order.entity.Order;
import cn.tedu.order.feign.AccountClient;
import cn.tedu.order.feign.EasyIdClient;
import cn.tedu.order.feign.StorageClient;
import cn.tedu.order.mapper.OrderMapper;
import cn.tedu.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Autowired
    private EasyIdClient easyIdClient;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private StorageClient storageClient;

    @Override
    @Transactional
    @GlobalTransactional
    public void create(Order order) {
        //TODO 调用发号器获取订单id
        String s = easyIdClient.nextId("order_business");
        Long orderId = Long.valueOf(s);

        order.setId(orderId);
        orderMapper.create(order);

        // 减少库存
        //storageClient.decrease(order.getProductId(),order.getCount());

        // 扣减账户
        //accountClient.decrease(order.getUserId(),order.getMoney());
    }
}
