package cn.tedu.rabbitmqspringboot.m2;

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
 * 合理分发
 * 1. 手动ack，spring集成后默认是手动ack，spring自动执行发送回执操作
 * 2. qos=1，yml配置 pre-fetch=1，(预抓取信息条数)默认spring设置时 250
 *
 * 持久化
 * 1. 队列持久化
 * 2. 消息持久化，spring默认把消息设置为持久消息
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
        return new Queue("task_queue",true);
    }

    /**
     * springboot 执行流程
     * 包扫描创建实例 --> 完成所有的依赖注入 --> @PostConstruct --> 后续步骤
     */
    @PostConstruct
    public void test(){
        // 为了使send()方法中的死循环不阻塞spring主线程的执行
        // 在新的线程中，执行send() 方法
        // lambda 表达式简化上面匿名内部类的语法格式
        new Thread(()->p.send()).start();
    }
}

