package m1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 9:01
 * @description  简单模式的服务提供者
 */
public class Producer {
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
        /*
        参数：
            1.队列名
            2.是否是持久队列
            3.是否是排他队列(独占)
            4.是否自动删除
            5.队列其他属性参数设置
         */
        channel.queueDeclare("helloworld",false,false,false,null);

        //3.向helloworld队列发送消息
        /*
        参数
            1.""是一个默认的交换机
            2.队列名
            3.消息的其他参数属性
            4.发送的消息
         */
        channel.basicPublish("","helloworld",null,"helloworld".getBytes());
    }
}
