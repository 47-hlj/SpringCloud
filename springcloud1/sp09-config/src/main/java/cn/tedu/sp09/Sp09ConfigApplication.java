package cn.tedu.sp09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 47HLJ
 * @date 2021/7/26 12:00
 * @description
 */
@SpringBootApplication
@EnableConfigServer
public class Sp09ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp09ConfigApplication.class,args);
    }
}
