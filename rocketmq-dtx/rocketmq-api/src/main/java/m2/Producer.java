package m2;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/30 17:05
 * @description
 */
public class Producer {

    /**
     * 消息数据
     */
    static String[] messages = {
            "15103111039,创建",
            "15103111065,创建",
            "15103111039,付款",
            "15103117235,创建",
            "15103111065,付款",
            "15103117235,付款",
            "15103111065,完成",
            "15103111039,推送",
            "15103117235,完成",
            "15103111039,完成"
    };

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        // 1.创建生产者
        DefaultMQProducer p =new DefaultMQProducer("producer2");

        // 2.name server
        p.setNamesrvAddr("47.99.53.71:9876");

        // 3.启动
        p.start();

        // 4.发送消息，设置队列选择器用来选择队列
        for(String msg : messages){
            Long orderId = Long.valueOf(msg.split(",")[0]);

            Message message = new Message("Topic2", msg.getBytes());

            //消息，队列选择器
            p.send(message, new MessageQueueSelector() {
                /**
                 * (服务器的队列信息，消息，选择依据)
                 * @param list
                 * @param message
                 * @param o
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Long orderId = (Long) o;
                    int index = (int) (orderId % list.size());
                    return list.get(index);
                }
            },orderId);
        }
    }
}
