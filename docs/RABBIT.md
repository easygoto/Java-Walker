## 消息队列

### 1 奠定基础

#### 1.1 快速启动

> 若想使用队列, 先要声明队列, [相关的测试源码](../src/main/java/example/rabbit/quickstart)

#### 1.2 交换机的使用

> 交换机对消息队列的使用能够灵活起来, 可以通过 routingKey 来绑定相关的队列, [相关的测试源码](../src/main/java/example/rabbit/api/exchange)

> direct: 处理完全匹配路由 key 的队列
>
> fanout: 处理所有绑定的队列, 无路由 key, 即使指定也来者不拒, 速度最快
>
> topic: 处理匹配规则的路由 key 的队列, 是用途最为广泛的类型

#### 1.3 可靠性投递

> 自动应答和手工签收, 推荐使用手动签收
>
> [相关的测试源码](../src/main/java/example/rabbit/api/ack)

> 不匹配的消息回退到生产端
>
> [相关的测试源码](../src/main/java/example/rabbit/api/returnlistener)

> 确认消息, no ack 出现的情况 (磁盘写满了, MQ出现了一些异常, 或者Queue容量到达上限了)
>
> [相关的测试源码](../src/main/java/example/rabbit/api/confirm)

#### 1.4 削峰

> [相关的测试源码](../src/main/java/example/rabbit/api/limiting)

#### 1.5 死信队列

> [相关的测试源码](../src/main/java/example/rabbit/api/dlx)

### 2 Spring

#### 2.1 基础方法

> RabbitAdmin (队列管理)

> RabbitTemplate (简化版的发送消息)

#### 2.2 监听容器

> SimpleMessageListenerContainer (监听容器)

```
SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
container.setQueues(queue001(), queue002(), queue003(), queueImage(), queuePdf());
// 当前消费者数量
container.setConcurrentConsumers(1);
// 最大消费者数量
container.setMaxConcurrentConsumers(5);
// 是否重回队列
container.setDefaultRequeueRejected(false);
// 自动签收
container.setAcknowledgeMode(AcknowledgeMode.AUTO);
// 消费者标签生成策略
container.setConsumerTagStrategy(queue -> queue + ": " + UUID.randomUUID().toString());
```

> ChannelAwareMessageListener (消费监听器)
>
> 消费监视者, 启动 Application 后, 用单元测试发送四条消息, 但在命令行中看只消费了两条数据?
>
> 原因是启动单元测试也会启动消费者监听容器, 所以另外两条是被自己消费了, 如果只启动单元测试, 会正常消费四条

```
container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
    String msg = new String(message.getBody());
    System.out.println("---------------- 消费者: " + msg);
});
```



