package m1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 9:14
 * @description   简单模式的服务消费者
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.连接服务器
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("47.99.53.71");
        //默认端口，可以不设置
        factory.setPort(5672);
        factory.setUsername("47hlj");
        factory.setPassword("hlj172532");
        //连接
        Connection connection=factory.newConnection();
        //通信通道
        Channel channel=connection.createChannel();

        //2.创建队列 helloworld
        //如果队列已经存在，不会重复创建
        channel.queueDeclare("helloworld",false,false,false,null);

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


        //4.开始接受信息  收到的消息会传递到一个回调对象进行处理 (队列名,true,处理消息的回调对象,取消消息处理时的回调对象)
        /*
        第二个参数 autoAck
        - autoAck=true  自动确认(Acknowledgment)
        - autoAck=false 手动确认
         */
        channel.basicConsume("helloworld",true,deliverCallback,cancelCallback);
    }
}
