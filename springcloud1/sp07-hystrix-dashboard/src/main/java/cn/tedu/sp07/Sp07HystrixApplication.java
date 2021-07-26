package cn.tedu.sp07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author 47HLJ
 * @date 2021/7/21 17:05
 * @description
 */

@SpringBootApplication
@EnableHystrixDashboard
public class Sp07HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp07HystrixApplication.class,args);
    }
}
