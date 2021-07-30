package m1;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/30 15:43
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        // 1. 新建消费者实例
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer1");
        // 2.设置 name server
        c.setNamesrvAddr("47.99.53.71:9876");
        // 从哪订阅消息
        /*
        标签：
            *   -- 所有标签
            Tag1
            Tag1 || Tag2 || Tag3   -- 接收多种标签的消息
         */
        c.subscribe("Topic1", "Tag1");
        // 3.设置消息监听器
        /*
        Concurrently -- 并发
        会启动多个线程，并发的处理消息
         */
        c.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String s = new String(msg.getBody());
                    System.out.println("收到： "+s);
                }
                // 返回消息处理状态（回执）
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                // 通知服务器，稍后重新投递这条消息
                // return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        // 4.启动
        c.start();
    }
}
