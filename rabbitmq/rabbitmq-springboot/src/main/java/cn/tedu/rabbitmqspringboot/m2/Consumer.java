package cn.tedu.rabbitmqspringboot.m2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 10:12
 * @description
 * 每个@RabbitListener 注解都会注册成为一个消费者
 */
@Component
public class Consumer {

    @RabbitListener(queues = "task_queue")
    public void receive1(String s){
        System.out.println("消费者1收到："+s);
    }

    @RabbitListener(queues = "task_queue")
    public void receive2(String s){
        System.out.println("消费者2收到："+s);
    }

}
