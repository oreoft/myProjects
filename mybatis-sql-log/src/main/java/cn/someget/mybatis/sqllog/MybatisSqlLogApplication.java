package cn.someget.mybatis.sqllog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示mybatisPlus的性能分析/sql日志打印
 * @author oreoft
 */
@SpringBootApplication
public class MybatisSqlLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSqlLogApplication.class, args);
    }

}
