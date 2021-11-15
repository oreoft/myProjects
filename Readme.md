# my-projects

这是我自己的小代码库, 如果里面的代码对你有帮助那最好. 代码都遵循P3C规范, 注释翔实, 还有部分原理解释, 导入即用.

1. mix-tools
   > 自己总结的工具类, 一般是自己业务里面比较棘手的处理, 和市面上常见轮子不太一样, 例如stream中根据指定key去重, stream中
   > 一次流进行最值均值计算等等.总的来说就是自己一些骚操作记录一下, 下次用的话可以直接找.
2. redis-expire 
   > redis过期回调配置, help中有redis配置教程
3. redis-multi-bean 
   > redis在springboot配置(不使用SpringData的自动装配,并且实现多数据源), 当然也支持单个库, 使用Lettuce线程池
   > 多个实例多个库, 最少代码配置
4. mongo-multi-bean 
   > mongo在springboot配置(不使用SpringData的自动装配,并且实现多数据源)
   > 最少代码配置, 支持打印执行语句, 支持字段自动小驼峰转下划线


   
<br></br>
> p.s. 为了方便拷贝, 每个项目都是单独项目, my-projects只是做文件夹作用, 没有模块之间的继承


