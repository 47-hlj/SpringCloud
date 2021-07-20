package cn.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 47HLJ
 * @date 2021/7/20 16:15
 * @description
 */
@EnableZuulProxy
@SpringBootApplication
public class Sp06ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp06ZuulApplication.class,args);
    }
}
