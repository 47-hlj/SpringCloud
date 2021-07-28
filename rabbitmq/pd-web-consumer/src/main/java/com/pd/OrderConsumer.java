package com.pd;

import com.pd.pojo.PdOrder;
import com.pd.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 8:51
 * @description
 * 使用@RabbitListener注解，从指定的队列接受消息，自动反序列化成order对象
 */

@Component
@RabbitListener(queues = "orderQueue")
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    /**
     * 配合 @RabbitListener,用来指定处理消息的方法 只能有一个
     * @param order
     */
    @RabbitHandler
    public void receive(PdOrder order) throws Exception {
        orderService.saveOrder(order);
        System.out.println("------------------订单已保存");
    }


}
