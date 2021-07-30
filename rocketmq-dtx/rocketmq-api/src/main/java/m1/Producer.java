package m1;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Scanner;

/**
 * @author 47HLJ
 * @date 2021/7/30 15:36
 * @description
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 1.新建生产者实例
        DefaultMQProducer p = new DefaultMQProducer("producer1");

        // 2.设置连接
        p.setNamesrvAddr("47.99.53.71:9876");

        // 3.启动 -- 和服务器建立连接
        p.start();

        // 4.发送消息封装到 Message 对象
        // 发送 Message
        while (true){
            System.out.print("输入消息：");
            String s = new Scanner(System.in).nextLine();
            //Topic 相当于是一级分类，Tag 相当于二级分类
            Message msg = new Message("Topic1","Tag1",s.getBytes());

            //设置延迟消息
            msg.setDelayTimeLevel(3);

            SendResult result = p.send(msg);
            System.out.println(result);
        }
    }
}
