package cn.someget.redis.multibean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * redis多数据源配置(当然单数据源也可以用)
 * @author oreoft
 * @date 2021-06-30 11:06
 */
@SpringBootApplication
@EnableConfigurationProperties
public class RedisMultiBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisMultiBeanApplication.class, args);
    }

}
