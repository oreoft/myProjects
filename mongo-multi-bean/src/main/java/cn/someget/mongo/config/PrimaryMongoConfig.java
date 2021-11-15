package cn.someget.mongo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * 把primary的mongo注入到ioc中
 *
 * @author oreoft
 * @date 2021-09-23 11:09
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.primary")
public class PrimaryMongoConfig extends AbstractMongoConfig {

  /**
   * 创建mongoTemplate
   *
   * @return 把primaryMongoTemplate优先注入到ioc
   */
  @Primary
  @Override
  @Bean(name = "primaryMongoTemplate")
  public MongoTemplate getMongoTemplate(@Qualifier("primaryFactory") MongoDatabaseFactory factory,
                                        @Qualifier("primaryConverter")MappingMongoConverter converter) {
    // 创建template
    return new MongoTemplate(factory, converter);
  }

  /**
   * 创建映射转换器(驼峰转下划线), 后面的数据源记得bean要取名字, 并且要Qualifier
   * @param applicationContext 上下文
   * @return 转换器
   */
  @Primary
  @Bean("primaryConverter")
  public MappingMongoConverter getMappingConverter(ApplicationContext applicationContext,
                                                   @Qualifier("primaryFactory") MongoDatabaseFactory factory) {

    return getMappingMongoConverter(applicationContext, factory);
  }

  /**
   * 这个工厂类后面两个bean都要用到, 所以也注册一下
   * 后面的数据源记得bean要取名字, 并且要Qualifier
   * @return MongoDatabaseFactory
   */
  @Primary
  @Bean("primaryFactory")
  public MongoDatabaseFactory getMongoDbFactory() {
    // 根据不同类实现的抽象方法不一样,创建不同配置的工厂
    return mongoDbFactory();
  }


}
