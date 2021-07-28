package cn.tedu.rabbitmqspringboot.m3;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:10
 * @description
 */
@Component
public class Consumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,  //队列,不给参数会创建随机命名队列，非持久，独占，自动删除
            exchange =  @Exchange(name = "logs",declare = "false")  //交换机, declare="false"不在服务器重复创建交换机，只是使用这个交换机
    ))
    public void receive1(String s) {
        System.out.println("消费者1收到： "+s);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,  //队列,不给参数会创建随机命名队列，非持久，独占，自动删除
            exchange =  @Exchange(name = "logs",declare = "false")  //交换机, declare="false"不在服务器重复创建交换机，只是使用这个交换机
    ))
    public void receive2(String s) {
        System.out.println("消费者2收到： "+s);

    }
}
