package cn.someget.redisexpire.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 监听消息, 并且处理
 * @author oreoft
 * @date 2021-05-23 09:05
 */
@Slf4j
public class RedisExpiredListener implements MessageListener {

    /**
     * 客户端监听订阅的topic，当有消息的时候，会触发该方法, 建议不要再onmessage里面处理业务, 发mq去处理
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        log.info("收到channel: {}的消息,已经过期的key是{} ", new String(channel), new String(body));
    }

}
