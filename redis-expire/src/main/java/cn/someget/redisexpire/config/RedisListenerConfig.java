package cn.someget.redisexpire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 创建监听容器
 * @author oreoft
 * @date 2021-05-23 09:05
 */
@Configuration
public class RedisListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        // new出监听容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 加入连接源
        container.setConnectionFactory(connectionFactory);
        // 加入监听事件, 第一个参数是我们设置的监听器
        container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@0__:expired"));
        return container;
    }
}