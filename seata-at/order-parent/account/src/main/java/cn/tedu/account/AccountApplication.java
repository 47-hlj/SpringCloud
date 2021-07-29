package cn.tedu.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 47HLJ
 * @date 2021/7/28 15:50
 * @description
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
    }
}
