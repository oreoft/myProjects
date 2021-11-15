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
 * 各个redis连接源10库的客户端创建
 * @author oreoft
 */
@Setter
@Configuration
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "spring.redis10")
public class RedisConfig10 extends RedisBaseConfig{

    /**
     * 数据库号
     */
    private int database;

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
     * java10库连接工厂
     *
     * @return 对应连接连接工厂
     */
    @Primary
    @Bean(name = "connectionFactor10Java")
    LettuceConnectionFactory createJavaLettuceConnectionFactory() {
        return getLettuceConnectionFactory(hostJava, passwordJava, database);
    }

    /**
     * golang10库的连接工厂
     *
     * @return 对应连接连接工厂
     */
    @Bean(name = "connectionFactor10Golang")
    LettuceConnectionFactory createGolangLettuceConnectionFactory() {
        return getLettuceConnectionFactory(hostGolang, passwordGolang, database);
    }

    /**
     * 创建java10库的redis客户端
     * @param factory 对应连接连接工厂
     * @return redis客户端
     */
    @Bean(name = "redisTemplate10Java")
    public RedisTemplate<String, Object> redisTemplate0Java(@Qualifier("connectionFactor10Java") RedisConnectionFactory factory) {
        return getRedisTemplate(factory);
    }

    /**
     * 创建golang库的redis客户端
     * @param factory 对应连接连接工厂
     * @return redis客户端
     */
    @Bean(name = "redisTemplate10Golang")
    public RedisTemplate<String, Object> redisTemplate0Golang(@Qualifier("connectionFactor10Golang") RedisConnectionFactory factory) {
        return getRedisTemplate(factory);
    }

}