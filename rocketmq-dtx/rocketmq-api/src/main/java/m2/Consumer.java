package m2;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/30 17:29
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        // 1. 新建消费者实例
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer2");
        // 2.设置 name server
        c.setNamesrvAddr("47.99.53.71:9876");
        // 从哪订阅消息
        /*
        标签：
            *   -- 所有标签
            Tag1
            Tag1 || Tag2 || Tag3   -- 接收多种标签的消息
         */
        c.subscribe("Topic2","*");
        // 3.设置消息监听器
        /*
            Orderly --- 按消费顺序
            单线程处理消息
         */
        c.setMessageListener(new MessageListenerOrderly() {
            /**
             * 消息处理
             * @param list
             * @param consumeOrderlyContext
             * @return
             */
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    String s = new String(msg.getBody());
                    System.out.println("收到： "+s);
                }
                // 返回消息处理状态（回执）
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        // 4.启动
        c.start();
    }
}
