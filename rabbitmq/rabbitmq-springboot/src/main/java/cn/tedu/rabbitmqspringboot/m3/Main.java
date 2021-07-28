package cn.tedu.rabbitmqspringboot.m3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:10
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
    public FanoutExchange logs() {
        return new FanoutExchange("logs", false, false);
    }

    @PostConstruct
    public void test() {
        new Thread(() -> p.send()).start();
    }
}
