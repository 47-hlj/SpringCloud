package cn.tedu.sp04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 47HLJ
 * @date 2021/7/19 16:49
 * @description
 */
@EnableFeignClients
@SpringBootApplication
public class Sp04OrderserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp04OrderserviceApplication.class,args);
    }
}
