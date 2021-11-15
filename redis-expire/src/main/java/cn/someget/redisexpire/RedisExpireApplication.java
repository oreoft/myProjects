package cn.someget.redisexpire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis过期回调的小demo
 * @author oreoft
 * @date 2021-05-23 09:05
 */
@SpringBootApplication
public class RedisExpireApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisExpireApplication.class, args);
	}

}
