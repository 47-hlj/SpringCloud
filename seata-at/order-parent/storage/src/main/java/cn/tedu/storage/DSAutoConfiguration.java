package cn.tedu.storage;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author 47HLJ
 * @date 2021/7/29 10:39
 * @description
 */
@Configuration
public class DSAutoConfiguration {
    /**
     * 创建原始的数据源对象
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new HikariDataSource();
    }

    /**
     * 创建数据源代理对象
     * @param ds
     * @return
     * 使用@Primary注解，首选对象
     */
    @Primary
    @Bean
    public DataSource dataSourceProxy(DataSource ds){
        return new DataSourceProxy(ds);
    }
}
