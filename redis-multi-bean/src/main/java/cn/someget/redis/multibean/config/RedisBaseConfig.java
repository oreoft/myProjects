package cn.someget.redis.multibean.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.api.StatefulConnection;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 这个是通用方法,
 * <p>key采用String的序列化方式
 * hash的key也采用String的序列化方式
 * value序列化方式采用jackson
 * hash的value序列化方式采用jackson</p>
 * @author oreoft
 * @date 2021-06-30 11:06
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisBaseConfig {
    /**
     * 端口号
     */
    private int port;

    /**
     * 最大活跃连接数
     */
    private int maxActive;

    /**
     * 最大等待数
     */
    private int maxWait;

    /**
     * 最大空闲连接数
     */
    private int maxIdle;

    /**
     * 最小空闲连接数
     */
    private int minIdle;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 数据库号(子类用)
     */
    protected int database;

    /**
     * 获取连接池连接工厂
     *
     * @param host     主机号
     * @param passWord 鉴权密码
     * @return 连接工厂
     */
    public LettuceConnectionFactory getLettuceConnectionFactory(String host, String passWord, int database) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(passWord));

        //连接池配置
        GenericObjectPoolConfig<StatefulConnection<String, Object>> genericObjectPoolConfig =
                new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);

        //redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder = LettucePoolingClientConfiguration.builder().
                commandTimeout(Duration.ofMillis(timeout));

        builder.shutdownTimeout(Duration.ofMillis(timeout));
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();

        //根据配置和客户端配置创建连接
        return new
                LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
    }

    /**
     * 获取template(设置连接源,设置序列化)
     *
     * @param factory 连接工厂
     * @return redis客户端
     */
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
