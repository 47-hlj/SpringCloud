package cn.tedu.rabbitmqspringboot.m5;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:40
 * @description
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate at;

    // 发送方法需要手动调用
    public void send() {
        while (true) {
            System.out.println("输入消息：");
            String s = new Scanner(System.in).nextLine();
            System.out.println("输入路由键：");
            String k = new Scanner(System.in).nextLine();
            at.convertAndSend("topic_logs", k, s);
        }
    }
}
