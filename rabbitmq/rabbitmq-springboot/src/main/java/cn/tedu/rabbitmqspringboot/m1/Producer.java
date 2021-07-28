package cn.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 10:00
 * @description
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate at;

    // 发送方法需要手动调用
    public void send(){
        // 向hello world队列发送消息
        at.convertAndSend("helloworld","Hello World!");
    }
}
