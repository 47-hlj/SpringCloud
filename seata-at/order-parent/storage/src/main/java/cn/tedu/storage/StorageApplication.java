package cn.tedu.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:02
 * @description
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class,args);
    }
}
