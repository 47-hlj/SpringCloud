package cn.tedu.rabbitmqspringboot.m5;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:40
 * @description
 */
@SpringBootApplication
public class Main {
    @Autowired
    private Producer p;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public TopicExchange logs() {
        return new TopicExchange("topic_logs", false, false);
    }

    // 自己创建第一个消费者的队列
    // 放入spring容器对象名称是 rndQueue
    //    rndQueue ----> Queue实例
    @Bean
    public Queue rndQueue() {
        String q = UUID.randomUUID().toString();
        return new Queue(q,false,true,true);
    }

    @PostConstruct
    public void test() {
        new Thread(() -> p.send()).start();
    }
}
