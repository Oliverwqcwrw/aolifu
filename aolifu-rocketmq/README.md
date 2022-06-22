
1、保持依赖最小化

2、保持简单化

3、生产者可以连接到不同的NameServer

4、消费者可以连接到不同的NameServer

5、职责单一原则（相比较于之前剥离了redis和告警），官方不建议使用messageId作为幂等

6、扩展性强（添加异步、单向发送消息）

7、接入简单（让每个使用者很清晰的了解到如何接入RocketMQ）

8、支持所有的个性化参数配置（包括Producer和Consumer）

9、异常由业务方处理

10、统一的api，防止ctrl c + ctrl v 的重复工作