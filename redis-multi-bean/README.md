# Getting Started

### Introduction
这是redis的多数据源的demo, 里面其实比较复杂, 正常小项目可能就是一个reids
用自动装配的或者是写一个bean就够了, 但是如果遇到有多个redis实例, 并且需要用
多个实例的多个库, 配置就很麻烦, 我目前也没想到很好地解决这个动态多数据源的办法,
只能这样写, 每次用的时候bean注入的时候@Qualifier指定你要用的redistemplate(如果不指定默认使用primary创建的bean)


## Reference Documentation
none