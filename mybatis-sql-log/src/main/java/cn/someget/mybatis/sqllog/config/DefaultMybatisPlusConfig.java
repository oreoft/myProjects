package cn.someget.mybatis.sqllog.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * mybatis-plus性能分析sql日志打印插件
 *
 * @author oreoft
 */
@Configuration
public class DefaultMybatisPlusConfig {


    /**
     * 打印 sql，性能分析拦截器
     * 因为每次sql都需要做拦截, 会有性能损耗
     * 所以建议只在dev或者test环境下方便debug的时候查看
     * 如果多个环境 -> @Profile({"dev", "test"})
     * @return PerformanceInterceptor bean
     */
    @Bean
    @Profile("mp")
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置格式化, 类似于datagrip的美化sql语句, 在控制台好看一些
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
