package cn.someget.redis.multibean.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * 各个redis连接源0库的客户端创建
 * @author oreoft
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis0")
public class RedisConfig0 extends RedisBaseConfig {

    /**
     * java库的host
     */
    private String hostJava;

    /**
     * java库的密码
     */
    private String passwordJava;

    /**
     * golang库的host
     */
    private String hostGolang;

    /**
     * golang库的密码
     */
    private String passwordGolang;

    /**
     * java0库连接工厂
     *
     * @return 对应连接连接工厂
     */
    @Primary
    @Bean(name = "connectionFactor0Java")
    LettuceConnectionFactory createJavaLettuceConnectionFactory() {
        return getLettuceConnectionFactory(hostJava, passwordJava, database);
    }

    /**
     * golang0库的连接工厂
     *
     * @return 对应连接连接工厂
     */
    @Bean(name = "connectionFactor0Golang")
    LettuceConnectionFactory createGolangLettuceConnectionFactory() {
        return getLettuceConnectionFactory(hostGolang, passwordGolang, database);
    }

    /**
     * 创建java0库的redis客户端
     * @param factory 对应连接连接工厂
     * @return redis客户端
     */
    @Primary
    @Bean(name = "redisTemplate0Java")
    public RedisTemplate<String, Object> redisTemplate0Java(@Qualifier("connectionFactor0Java") RedisConnectionFactory factory) {
        return getRedisTemplate(factory);
    }

    /**
     * 创建golang库的redis客户端
     * @param factory 对应连接连接工厂
     * @return redis客户端
     */
    @Bean(name = "redisTemplate0Golang")
    public RedisTemplate<String, Object> redisTemplate0Golang(@Qualifier("connectionFactor0Golang") RedisConnectionFactory factory) {
        return getRedisTemplate(factory);
    }

}