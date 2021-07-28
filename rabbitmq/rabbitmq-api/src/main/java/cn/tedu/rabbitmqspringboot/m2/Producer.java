package cn.tedu.rabbitmqspringboot.m2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author 47HLJ
 * @date 2021/7/27 9:41
 * @description
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
        channel.queueDeclare("task_queue",true,false,false,null);


        //3.发送消息
        while(true){
            System.out.print("输入消息：");
            String s=new Scanner(System.in).nextLine();
            //在第三个参数添加持久参数，持久化常量
            channel.basicPublish("","task_queue", MessageProperties.PERSISTENT_TEXT_PLAIN,s.getBytes());
        }
    }
}
