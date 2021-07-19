package cn.tedu.sp05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 47HLJ
 * @date 2021/7/19 17:47
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class Sp05EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp05EurekaApplication.class,args);
    }
}
