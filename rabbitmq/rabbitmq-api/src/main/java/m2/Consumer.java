package m2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 9:48
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

        //2.创建队列 helloworld
        //如果队列已经存在，不会重复创建
        channel.queueDeclare("task_queue", true, false, false, null);

        //3.创建回调对象
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                byte[] a=message.getBody();
                String s=new String(a);
                System.out.println("收到："+s);
                // 模拟阻塞的消息
                // 遍历字符串找'.',每找到一个都暂停一秒
                for (int i = 0; i < s.length(); i++) {
                    if('.'==s.charAt(i)){
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){

                        }
                    }
                }
                // 发送回执
                //(回执，是否把之前收到过的消息一起进行确认)
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                System.out.println("--------------消息处理结束\n");
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
            }
        };

        //每次只接受一条消息，处理完之前不接收下一条
        channel.basicQos(1);

        //4.接受消息
        /*
        手动ack qos才能使用
         */
        channel.basicConsume("task_queue",false,deliverCallback,cancelCallback);
    }
}
