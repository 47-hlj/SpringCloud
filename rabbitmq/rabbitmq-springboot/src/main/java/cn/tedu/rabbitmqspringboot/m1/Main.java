package cn.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author 47HLJ
 * @date 2021/7/28 10:14
 * @description
 */
@SpringBootApplication
public class Main {
    @Autowired
    private Producer p;

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Bean
    public Queue helloworldQueue(){
        // 非持久队列，默认是持久
        return new Queue("helloworld",false);
    }

    /**
     * springboot 执行流程
     * 包扫描创建实例 --> 完成所有的依赖注入 --> @PostConstruct --> 后续步骤
     */
    @PostConstruct
    public void test(){
        p.send();
    }
}

