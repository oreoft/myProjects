package cn.someget.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * mongo多数据源配置(当然单数据源也可以用)
 * @author oreoft
 * @date 2021-09-12 11:06
 */
@SpringBootApplication
@EnableConfigurationProperties
public class MongoMultiBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoMultiBeanApplication.class, args);
    }

}
