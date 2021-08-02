package m3;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Scanner;


/**
 * @author 47HLJ
 * @date 2021/8/2 11:16
 * @description
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        // 1.创建事务消息生产者
        TransactionMQProducer p = new TransactionMQProducer("producer3");
        // 2.设置 name server
        p.setNamesrvAddr("47.99.53.71:9876");
        // 3.设置事务消息监听器
        p.setTransactionListener(new TransactionListener() {
            // 执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                if (Math.random() < 1) { // 100%
                    return LocalTransactionState.UNKNOW;
                }

                System.out.println("执行本地事务："+arg);

                if (Math.random() < 0.5) {
                    System.out.println("本地事务成功，提交消息");
                    // 为了之后处理消息回查，需要存储事务状态
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else {
                    System.out.println("本地事务失败，回滚消息");
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }

                //一般不会在本地事务方法中，直接返回这种状态
                //一般是由于网络中断，服务器不知道当前事务的状态
                //return LocalTransactionState.UNKNOW;
            }
            // 用来处理 Rocketmq 服务器的反向事务状态回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("服务器正在回查事务状态");
                // 从数据库查询事务状态，再向服务器返回
                return LocalTransactionState.UNKNOW;
            }
        });
        // 4.启动
        p.start();
        // 5.发送事务消息,会触发监听器执行本地事务
        while (true) {
            System.out.print("输入消息： ");
            String s = new Scanner(System.in).nextLine();
            Message msg = new Message("Topic3", s.getBytes());
            p.sendMessageInTransaction(msg, "本地事务需要的一些业务数据参数");
        }
    }
}
