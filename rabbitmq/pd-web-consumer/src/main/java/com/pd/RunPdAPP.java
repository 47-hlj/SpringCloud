package com.pd;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.pd.mapper")
public class RunPdAPP{

	public static void main(String[] args) {
		SpringApplication.run(RunPdAPP.class, args);
	}


	/**
	 * 使用spring封装的Queue对象，提供队列参数
	 * RabbitAutoConfiguration自动配置类中，会在spring容器中
	 * 自动发现Queue实例，使用它的参数连接服务器创建队列
	 * @return
	 */
	@Bean
	public Queue orederQueue(){
		return new Queue("orderQueue",true,false,false);
	}
}
