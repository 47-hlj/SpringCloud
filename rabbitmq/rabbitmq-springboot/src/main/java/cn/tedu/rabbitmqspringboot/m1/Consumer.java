package cn.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 10:12
 * @description
 */
@Component
@RabbitListener(queues = "helloworld")
public class Consumer {
    @RabbitHandler
    public void receive(String s){
        System.out.println("收到："+s);
    }
}
