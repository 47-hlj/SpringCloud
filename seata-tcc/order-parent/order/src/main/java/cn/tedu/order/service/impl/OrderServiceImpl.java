package cn.tedu.order.service.impl;

import cn.tedu.order.entity.Order;
import cn.tedu.order.feign.AccountClient;
import cn.tedu.order.feign.EasyIdClient;
import cn.tedu.order.feign.StorageClient;
import cn.tedu.order.mapper.OrderMapper;
import cn.tedu.order.service.OrderService;
import cn.tedu.order.tcc.OrderTccAction;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Autowired
    private EasyIdClient easyIdClient;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private StorageClient storageClient;

    @Autowired
    private OrderTccAction orderTccAction;

    @GlobalTransactional
    @Override
    public void create(Order order) {
        //TODO 调用发号器获取订单id
        String s = easyIdClient.nextId("order_business");
        Long orderId = Long.valueOf(s);
        order.setId(orderId);

        //不再直接执行业务数据操作，而是调用 TccAction 的第一阶段方法，冻结数据
        //orderMapper.create(order);


        //orderTccAction是一个动态代理对象，使用AOP切入了代码，
        //在切面代码中会新建上下文对象，传入到原始方法
        orderTccAction.prepare(
                null,
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getCount(),
                order.getMoney());

//        // 减少库存
//        storageClient.decrease(order.getProductId(),order.getCount());
//
//        // 扣减账户
//        accountClient.decrease(order.getUserId(),order.getMoney());
    }
}
