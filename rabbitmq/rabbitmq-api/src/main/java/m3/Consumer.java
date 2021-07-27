package m3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 10:48
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.连接服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.99.53.71");
        //默认端口，可以不设置
        factory.setPort(5672);
        factory.setUsername("47hlj");
        factory.setPassword("hlj172532");
        //连接
        Connection connection = factory.newConnection();
        //通信通道
        Channel channel = connection.createChannel();

        //2.创建自己的队列，绑定到交换机，此队列不共享独占，消费者离线，自动删除，不用持久化
        //1.创建随即队列  2.创建交换机   3.绑定
        String queue= UUID.randomUUID().toString();
        channel.queueDeclare(queue,false,true,true,null);
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);
        //第三个参数对fanout交换机无效
        channel.queueBind(queue,"logs","");

        //3.创建回调对象
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                byte[] a=message.getBody();
                String s=new String(a);
                System.out.println("收到："+s);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
            }
        };

        //4.从队列来接受消息
        channel.basicConsume(queue,true,deliverCallback,cancelCallback);
    }
}
