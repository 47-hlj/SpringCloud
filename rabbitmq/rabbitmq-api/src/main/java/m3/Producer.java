package m3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 10:42
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
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

        //3.向logs交换机发送消息
        while (true){
            System.out.print("输入消息：");
            String s=new Scanner(System.in).nextLine();
            //在第三个参数添加持久参数，持久化常量
            channel.basicPublish("logs","", null,s.getBytes());
        }
    }
}