package cn.tedu.rabbitmqspringboot.m5;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 47HLJ
 * @date 2021/7/28 11:40
 * @description
 */
@Component
public class Consumer {
    @RabbitListener(bindings = @QueueBinding(
            // 访问spring容器中的 rndQueue, 获取它的name属性
            // 使用 SPEL 访问spring容器中的对象
            value = @Queue(name = "#{rndQueue.name}", declare = "false"),
            exchange =  @Exchange(name = "topic_logs",declare = "false"),
            key = {"*.orange.*"}
    ))
    public void receive1(String s) {
        System.out.println("消费者1收到： "+s);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange =  @Exchange(name = "topic_logs",declare = "false"),
            key = {"*.*.rabbit", "lazy.#"}
    ))
    public void receive2(String s) {
        System.out.println("消费者2收到： "+s);
    }
}
