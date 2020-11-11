# novel-subscription
> 这是使用 Spring Cloud 微服务来实现的小说订阅系统。

[![Build Status][travis-image]][travis-url]

本项目使用 Spring Cloud 技术栈来搭建，构建了一个小说订阅系统的所有后端的 API。本项目包括了注册中心（Eureka）、网关（Zuul）、邮件、小说和用户服务，scrapy 由于是用 Python 写的，所以单独放到[novel-subscription-scrapy](https://github.com/Alexhuihui/novel-subscription-scrapy)中了。

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g87ckve9stj30qo0k0wex.jpg)

## Introduce

用户能够注册，并订阅喜欢的小说。然后系统就会在该小说更新后立刻发送更新的小说章节到用户的邮箱，使得用户不用每时每刻都要关注小说的更新状况。下面简单介绍一下各个模块的功能：

### novel-subscription-eurka

微服务的注册中心，本项目在开发环境只启动了一个注册中心。实际在线上部署的使用了高可用的双注册中心，分别部署在2台不同的服务器上。

### novel-subscription-zuul

通过网关访问所有的后端服务

### novel-subscription-service

包含了所有的业务模块

#### common

其它模板通信时或者都要使用的某些类（实体类、工具类）

#### mail

邮件服务，肩负着系统最后的最后一环——发送更新小说的邮件给用户

#### fiction

提供相关的小说信息的查询服务

#### user

本系统中最重要的一环，包含用户的所有操作的接口（注册、订阅小说）:

1. 监听 Redis 的 novel 频道
    1. scrapy 爬虫会定时爬取网页，发现小说更新后会向 Redis 的 novel 频道发送更新小说的 novel_id
    2. 当 user 服务监听到有小说更新后，向订阅了该本小说的用户发送更新邮件
    
2. 最热小说、最新小说（后续更新中）

## Usage example

暂时未对外开放，如需尝试订阅小说的朋友请联系我下方的邮箱。

## Development Build

1. redis
2. nginx
3. mysql
4. java
5. python(scrapy)


## Meta

Alex – [@Alexhuihui](https://github.com/Alexhuihui) – 2930807240@qq.com

## API DOC
[Api 文档](https://www.showdoc.com.cn/awyl)


## Contributing

1. Fork it (<https://github.com/Alexhuihui/novel-subscription/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

<!-- Markdown link & img dfn's -->
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics


