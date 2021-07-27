package m4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 11:10
 * @description
 */
public class Producer {
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

        //2.创建Fanout类型交换机
        channel.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);

        //3.向logs交换机发送消息
        while (true){
            System.out.print("输入消息：");
            String s=new Scanner(System.in).nextLine();
            System.out.print("输入路由键：");
            String k=new Scanner(System.in).nextLine();
            // 第二个参数对 fanout 交换机无效
            // 对默认交换机，路由键就是队列名
            channel.basicPublish("direct_logs",k, null,s.getBytes());
        }
    }
}
