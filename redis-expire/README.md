# Getting Started

### Introduction
这个项目是redis的过期回调,一个小demo

## redis也需要进行配置修改
1. vim 你redis安装目录/redis.conf
```json 
vim /usr/local/redis-6.0.10/redis.conf
```
2. 搜索`notify-keyspace-events` `/notify-keyspace-even`
![图](https://mypicgogo.oss-cn-hangzhou.aliyuncs.com/tuchuang20210523102640.png)

3. 添加notify-keyspace-events Ex(如果注释了,把注释放开)
   这个Ex,后面的x代表的是监听事件, 我们只需要过期时间, 所以放一个x
   K：keyspace事件，事件以__keyspace@<db>__为前缀进行发布；         
   E：keyevent事件，事件以__keyevent@<db>__为前缀进行发布；         
   g：一般性的，非特定类型的命令，比如del，expire，rename等；        
   $：字符串特定命令；         
   l：列表特定命令；         
   s：集合特定命令；         
   h：哈希特定命令；         
   z：有序集合特定命令；         
   x：过期事件，当某个键过期并删除时会产生该事件；         
   e：驱逐事件，当某个键因maxmemore策略而被删除时，产生该事件；         
   A：g$lshzxe的别名，因此”AKE”意味着所有事件。


## Reference Documentation
1. [与Redis进行消息传递](https://spring.io/guides/gs/messaging-redis/)
2. [Redis键空间通知](https://redis.io/topics/notifications)