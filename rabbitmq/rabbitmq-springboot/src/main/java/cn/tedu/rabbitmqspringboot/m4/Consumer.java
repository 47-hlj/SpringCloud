package cn.tedu.rabbitmqspringboot.m4;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:35
 * @description
 */
@Component
public class Consumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange =  @Exchange(name = "direct_logs",declare = "false"),
            key = {"error"}
    ))
    public void receive1(String s) {
        System.out.println("消费者1收到： "+s);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange =  @Exchange(name = "direct_logs",declare = "false"),
            key = {"info", "error", "warning"}
    ))
    public void receive2(String s) {
        System.out.println("消费者2收到： "+s);
    }
}
