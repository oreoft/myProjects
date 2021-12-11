# Getting Started

### Introduction
这是mongo的多数据源, 如果普通项目工程的话只需要保留Primary就好,把Secondary给删掉. 相反如果需要
更多的库, 只需要实现AbstractMongoConfig加一个配置文件就好了.
值得注意的是, 这里我并不是使用SimpleMongoDbFactory来初始化,一方面是原来的工厂方法马上要过期,
另外一个是我设置了自定义转换器, 需要把小驼峰自动转成下划线(这样只有特殊字段才需要使用@Field注解了).


## Reference Documentation
none