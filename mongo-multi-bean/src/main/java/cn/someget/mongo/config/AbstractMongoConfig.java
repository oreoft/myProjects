package cn.someget.mongo.config;

import com.mongodb.client.MongoClients;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;

/**
 * 根据配置文件创建MongoDbFactory
 * 如果需要多加一个库, 则多加一个这个config的实现就好了
 * 相反目前默认有primary和secondary, 如果只需要一个则删掉一个类
 * @author oreoft
 * @date 2021-09-23 11:09
 */
@Setter
@Slf4j
public abstract  class AbstractMongoConfig {

    /**
     * 懂得都懂
     */
    private String host, database, username, password;
    private Integer port;


    /**
     * 创建公共的数据源工厂
     */
    protected MongoDatabaseFactory mongoDbFactory() {

      // 默认连接localhost:27017
      host = Optional.ofNullable(this.host).orElse("localhost");
      port = Optional.ofNullable(this.port).orElse(27017);

      // 如果测试环境没有账号和密码, 则直接连接
      if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
        // example: mongodb://host:port/dataName
        String connectionString ="mongodb://%s:%d/%s";
        String url = String.format(connectionString, host, port, database);
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(url), database);
      }

      // example: mongodb://user:password@host:port/dataName
      String connectionString = "mongodb://%s:%s@%s:%d/%s";
      String url = String.format(connectionString, username, password, host, port, database);
      return new SimpleMongoClientDatabaseFactory(MongoClients.create(url), database);
    }


    protected MappingMongoConverter getMappingMongoConverter(ApplicationContext applicationContext, MongoDatabaseFactory factory) {
        // 自动把驼峰转换成下划线, 只有特殊字段才需要使用@Field注解了
        MongoCustomConversions conversions = new MongoCustomConversions(Collections.emptyList());
        MongoMappingContext context = new MongoMappingContext();
        try {
            context.setInitialEntitySet(new EntityScanner(applicationContext)
                    .scan(Document.class, Persistent.class));
        } catch (ClassNotFoundException e) {
            log.warn("InitialEntitySet is occurred error, message: [{}]", e.getMessage());
        }
        context.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
        context.setSimpleTypeHolder(conversions.getSimpleTypeHolder());

        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), context);
        converter.setCustomConversions(conversions);
        return converter;
    }


    /**
     * 不同的子类去实现
     * @param factory mongo的工厂
     * @param converter mongo自己实现的转换器
     * @return MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate(MongoDatabaseFactory factory, MappingMongoConverter converter);
}
