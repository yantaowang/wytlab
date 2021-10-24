git config --global http.sslVerify "false"
1)JVM
  JAVA语言编译成字节码文件，执行时通过解释器翻译成本地机器码，实现跨平台。JVM在执行字节码时，实际上最终还是把字节码解释成具体平台上的机器指令执行，
通过助记符转成二进制码执行， JVM内存模型主要由堆内存、方法区、程序计数器、虚拟机栈和本地方法栈组成，
  JVM加载类有7个步骤：1、加载：找class文件， 2、验证：验证格式、依赖；3、准备：静态字段、方法表；4、解析，符号解析为引用；5、初始化，构造器，静态变量
赋值、静态代码块，6、使用；7、卸载
  JMM 规范明确定义了不同的线程之间，通过哪些方式，在什么时候可以看见其他线程保存到共享变量中的值；以及在必要时，如何对共享变量的访问进行同步
  CMS的六个阶段：
    1、初始标记，这个阶段伴随着 STW 暂停。初始标记的目标是标记所有的根对象，包括根对象直接引用的对象，以及被年轻代中所有存活对象所引用的
对象。
    2、并发标记，在此阶段，CMS GC 遍历老年代，标记所有的存活对象，从前一阶段 “Initial Mark” 找到的根对象开始算起。 “并发标记”阶段，
就是与应用程序同时运行，不用暂停的阶段  
    3、并发预清理，此阶段同样是与应用线程并发执行的，不需要停止应用线程。 因为前一阶段【并发标记】与程序并发运行，可能有一些引用关系已经发生了改变。
如果在并发标记过程中引用关系发生了变化，JVM 会通过“Card（卡片）”的方式将发生了改变的区域标记为“脏”区，这就是所谓的卡片标记（Card Marking）
    4、最终标记，最终标记阶段是此次 GC 事件中的第二次（也是最后一次）STW停顿。本阶段的目标是完成老年代中所有存活对象的标记
    5、并发清除，此阶段与应用程序并发执行，不需要 STW 停顿。JVM 在此阶段删除不再使用的对象，并回收他们占用的内存空间
    6、并发重置，此阶段与应用程序并发执行，重置 CMS 算法相关的内部数据，为下一次 GC 循环做准备
  GC总结：
  1、串行 GC（Serial GC）: 单线程执行，应用需要暂停；
  2、并行 GC（ParNew、Parallel Scavenge、Parallel Old）: 多线程并行地执行垃圾回收，关注与高吞吐
  3、CMS（Concurrent Mark-Sweep）: 多线程并发标记和清除，关注与降低延迟；
  4、G1（G First）: 通过划分多个内存区域做增量整理和回收，进一步降低延迟；
  5、ZGC（Z Garbage Collector）: 通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展；
  OOM:
  1、OutOfMemoryError: PermGen space/OutOfMemoryError: Metaspace
java.lang.OutOfMemoryError: PermGen space 的主要原因，是加载到内存中的类数量太多或体积太大，超过了 PermGen 区的大小。
解决办法：增大 PermGen/Metaspace
-XX:MaxPermSize=512m
-XX:MaxMetaspaceSize=512m
高版本 JVM 也可以：
-XX:+CMSClassUnloadingEnabled
  2、OutOfMemoryError: Unable to create new native thread
java.lang.OutOfMemoryError: Unable to create new native thread 错误是程序创建的线程数量已达到上限值的异常信息。
解决思路：
1. 调整系统参数 ulimit -a，echo 120000 > /proc/sys/kernel/threads-max
2. 降低 xss 等参数
3. 调整代码，改变线程创建和使用方式
  JVM调优
   解决这类问题，需要让年轻代存放得下暂存的数据，有两种简单的方法。
   一是增加年轻代的大小，设置 JVM 启动参数，类似这样：
   -Xmx64m -XX:NewSize=32m，程序在执行时，Full GC 的次数自然会减少很多，只会对 minor GC 的持续时间产生影响。
   二是减少每次业务处理使用的内存数量，也能得到类似的结果。
2)NIO
   IO 多路复用(IO multiplexing)，也称事件驱动 IO(event-driven IO)，就是在单个线程里同时监控多个套接字，通过 select 或poll 轮询所负责的所有 socket，当某个
socket 有数据到达了，就通知用户进程   
   IO 复用同非阻塞 IO 本质一样，不过利用了新的 select 系统调用，由内核来负责本来是请求进程该做的轮询操作。看似比非阻塞 IO 还多了一个系统调用开销，不过
因为可以支持多路 IO，才算提高了效率
   epoll（Linux 2.5.44内核中引入,2.6内核正式引入,可被用于代替 POSIX select 和 poll 系统调用）：
   （1）内核与用户空间共享一块内存
   （2）通过回调解决遍历问题
   （3）fd 没有限制，可以支撑10万连接
3)并发编程
   Thread 的状态改变操作
   1、Thread.sleep(long millis)，一定是当前线程调用此方法，当前线程进入 TIMED_WAITING 状态，但不释放对象锁，millis 后线程自动苏醒进入就绪状态。作用：给其它线程执行机会的最佳方式。
   2、t.join()/t.join(long millis)，当前线程里调用其它线程 t 的 join 方法，当前线程进入WAITING/TIMED_WAITING 状态，
   当前线程不会释放已经持有的对象锁，因为内部调用了 t.wait，所以会释放t这个对象上的同步锁。线程 t 执行完毕或者 millis 时间到，
   当前线程进入就绪状态。其中，wait 操作对应的 notify 是由 jvm 底层的线程执行结束前触发的
   Thread 的中断与异常处理
1. 线程内部自己处理异常，不溢出到外层（Future 可以封装）。
2. 如果线程被 Object.wait, Thread.join和Thread.sleep 三种方法之一阻塞，此时调用该线程的interrupt()方法
   那么该线程将抛出一个 InterruptedException 中断异常（该线程必须事先预备好处理此异常），从
   而提早地终结被阻塞状态。如果线程没有被阻塞，这时调用 interrupt() 将不起作用，直到执行到wait/sleep/join 时，
   才马上会抛出InterruptedException。
3. 如果是计算密集型的操作怎么办？
   分段处理，每个片段检查一下状态，是不是要终止
   ThreadPoolExecutor 提交任务逻辑:
1. 判断 corePoolSize 【创建】
2. 加入 workQueue
3. 判断 maximumPoolSize 【创建】
4. 执行拒绝策略处理器
   BlockingQueue 是双缓冲队列。BlockingQueue 允许两个线程同时向队列一个存储，一个取出操作。在保证并发安全的同时，提高了队列的存取效率。
   创建固定线程池的经验
   不是越大越好，太小肯定也不好：
   假设核心数为 N 1、如果是 CPU 密集型应用，则线程池大小设置为 N 或 N+1
   2、如果是 IO 密集型应用，则线程池大小设置为 2N 或 2N+2
ReadWriteLock 管理一组锁，一个读锁，一个写锁。
   读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的。 所有读写锁的实现必须确保写操作对读操作的内存影响。每次只能有一个写线程，但是同
   时可以有多个线程并发地读数据。ReadWriteLock 适用于读多写少的并发情况。  
   核心实现原理：
   1、volatile 保证读写操作都可见（注意不保证原子）；
   2、使用 CAS 指令，作为乐观锁实现，通过自旋重试保证写入。
LongAdder 的改进思路：
   1、AtomicInteger 和 AtomicLong 里的 value 是所有
   线程竞争读写的热点数据；
   2、将单个 value 拆分成跟线程一样多的数组 Cell[]； 3、每个线程写自己的 Cell[i]++，最后对数组求和。
4)Spring 和 ORM 等框架
   Spring Bean 生命周期
5)MySQL 数据库和 SQL
   事务实现
   
   undo log: 撤消日志
   •保证事务的原子性
   •用处: 事务回滚, 一致性读、崩溃恢复。
   •记录事务回滚时所需的撤消操作
   •一条 INSERT 语句，对应一条 DELETE 的 undo log
   •每个 UPDATE 语句，对应一条相反 UPDATE 的 undo log
   保存位置: •system tablespace（MySQL 5.7 默认）
   •undo tablespaces（MySQL 8.0 默认）
   回滚段（rollback segment）
   
   redo log: 重做日志
   •确保事务的持久性，防止事务提交后数据未刷新到磁盘就掉电或崩溃。
   •事务执行过程中写入 redo log，记录事务对数据页做了哪些修改。
   •提升性能: WAL（Write-Ahead Logging) 技术，先写日志，再写磁盘。
   •日志文件: ib_logfile0, ib_logfile1
   •日志缓冲: innodb_log_buffer_size
   •强刷: fsync()

MVCC: 多版本并发控制
•使 InnoDB 支持一致性读: READ COMMITTED 和 REPEATABLE READ
•让查询不被阻塞、无需等待被其他事务持有的锁，这种技术手段可以增加并发性能
•InnoDB 保留被修改行的旧版本
•查询正在被其他事务更新的数据时，会读取更新之前的版本
•每行数据都存在一个版本号, 每次更新时都更新该版本
•这种技术在数据库领域的使用并不普遍。 某些数据库, 以及某些 MySQL 存储引擎都不支持

无特殊需求下 Innodb 建议使用与业务无关的自增 ID 作为主键。
   InnoDB引擎使用聚集索引，数据记录本身被存于主索引（一颗 B + Tree）的叶子节点上，这就要求同一个叶子节点内（大小为一个内存页或磁盘页）的各条数据记录按主键顺序存放。因此每当有一条新的记录插入时，MySQL 会根据其主键将其插入适当的节点和位置，如果页面达到装载因子（InnoDB 默认为 15 / 16 ），则开辟一个新的页（节点）。
   1、如果表使用自增主键，那么每次插入新的记录，记录就会顺序添加到当前索引节点的后续位置，当一页写满，就会自动开辟一个新的页。
   这样就会形成一个紧凑的索引结构，近似顺序填满。由于每次插入时也不需要移动已有数据，因此效率很高，也不会增加很多开销在维护索引上。
   2、 如果使用非自增主键（如果身份证号或学号等），由于每次插入主键的值近似于随机，因此每次新纪录都要被插到现有索引页得中间某个位置：此时 MySQL 不得不为了将新记录插到合适位置而移动数据，甚至目标页面可能已经被回写到磁盘上而从缓存中清掉，此时又要从磁盘上读回来，这增加了很多开销，同时频繁的移动、分页操作造成了大量的碎片，得到了不够紧凑的索引结构，后续不得不通过OPTIMIZE TABLE 来重建表并优化填充页面。
   在使用 InnoDB 存储引擎时，如果没有特别的需要，请永远使用一个与业务无关的自增字段作为主键。
   mysql 在频繁地更新、删除操作，会产生碎片。而含碎片比较大的表，查询效率会降低。此时需对表进行优化，这样才会使查询变得更有效率。
主从复制原理
核心： 1、主库写 binlog
2、从库 relay log

6)分库分表
数据分类管理
这样的话，我们就可以采取一定的手段去优化系统：
1. 定义一周内下单但未支付的数据为热数据，同时放到数据库和内存；
2. 定义三个月内的数据为温数据，放到数据库，提供正常的查询操作；
3. 定义 3 个月到 3 年的数据，为冷数据，从数据库删除，归档到一些便宜的磁盘，用压缩的方式（比如
   MySQL 的 tokuDB 引擎，可以压缩到几十分之一）存储，用户需要邮件或者提交工单来查询，我们导
   出后发给用户；
4. 定义 3 年以上的数据为冰数据，备份到磁带之类的介质上，不提供任何查询操作。
   我们可以看到，上面都是针对一些具体场景，来分析和给出解决办法。那么通过在各种不同的场景下，都
   对现有的技术和手段进行一些补充，我们就会逐渐得到一个复杂的技术体系。
7)RPC和微服务
   参数配置
   通用参数以 consumer 端为准，如果 consumer 端没有设置，使用 provider 数值
   建议在 Provider 端配置的 Consumer 端属性有：
   timeout：方法调用的超时时间
   retries：失败重试次数，缺省是 2
   loadbalance：负载均衡算法，缺省是随机 random。
   actives：消费者端的最大并发调用限制，即当 Consumer 对一个服务的并发调用到上限后，新调用会阻塞直
   到超时，可以配置在方法或服务上。
   建议在 Provider 端配置的 Provider 端属性有：
   threads：服务线程池大小
   executes：一个服务提供者并行执行请求上限，即当 Provider 对一个服务的并发调用达到上限后，新调用会
   阻塞，此时 Consumer 可能会超时。可以配置在方法或服务上。

容器化部署
注册的IP问题，容器内提供者使用的 IP，如果注册到 zk，消费者无法访问。
两个解决办法：
1、docker 使用宿主机网络
docker xxx -net xxxxx
2、docker 参数指定注册的IP和端口，-e
DUBBO_IP_TO_REGISTRY — 注册到注册中心的 IP 地址
DUBBO_PORT_TO_REGISTRY — 注册到注册中心的端口
DUBBO_IP_TO_BIND — 监听 IP 地址
DUBBO_PORT_TO_BIND — 监听端口

实现一个新的RPC，主要是实现入口点 XXXProtocol；
同理，了解一个 RPC，也需要去 debug XXXProtocol。
例如 DubboProtocol： 1）想了解 Dubbo 协议下服务如何暴露，在 DubboProtocol 的 export 方法加断点；
2）想了解 Dubbo 协议下服务如何引用，在 DubboProtoco l的 protocolBindingRefer 方法加断
点；
3）想了解 Dubbo 协议下一个请求进来如何处理，在 DubboProtocol 的 requestHandler 的
reply 方法加断点
服务负载均衡（Service LoadBalance） 跟 Nginx 的负载均衡一样。
多个不同策略，原理不同，目的基本一致（尽量均匀）：
1、Random（带权重）== dubbo 默认的策略
2、RoundRobin（轮询）
3、LeastActive（快的多给）
4、ConsistentHashLoadBalance（同样参数请求到一个提供者）
相关工具-APM
APM：应用性能监控
- Apache Skywalking
- Pinpoint
- Zipkin
- Jaeger
  监控
- ELK
- promethus+Grafana
- MQ+时序数据库
  （InfluxDB/openTSDB 等）
  
8)分布式缓存
缓存加载时机
1、启动全量加载 ==> 全局有效，使用简单
2、懒加载
同步使用加载 ==>
- 先看缓存是否有数据，没有的话从数据库读取
- 读取的数据，先放到内存，然后返回给调用方
  延迟异步加载 ==>
- 从缓存获取数据，不管是否为空直接返回 ==>
- 策略1异步）如果为空，则发起一个异步加载的线程，负责加载数据
- 策略2解耦）异步线程负责维护缓存的数据，定期或根据条件触发更新
  
  缓存穿透
  问题：大量并发查询不存在的 KEY，导致都直接将压力透传到数据库。
  分析：为什么会多次透传呢？不存在一直为空。
  需要注意让缓存能够区分 KEY 不存在和查询到一个空值。
  解决办法：
  1、缓存空值的 KEY，这样第一次不存在也会被加载会记录，下次拿到有这个 KEY。 2、Bloom 过滤或 RoaringBitmap 判断 KEY 是否存在。
  3、完全以缓存为准，使用 延迟异步加载 的策略2，这样就不会触发更新。

缓存击穿
问题：某个 KEY 失效的时候，正好有大量并发请求访问这个 KEY。
分析：跟前面一个其实很像，属于比较偶然的。
解决办法：
1、KEY 的更新操作添加全局互斥锁。 2、完全以缓存为准，使用 延迟异步加载 的策略2，这样就不会触发更新。

缓存雪崩
问题：当某一时刻发生大规模的缓存失效的情况，会有大量的请求进来直接打到数据库，导致数据
库压力过大甚至宕机。
分析：一般来说，由于更新策略、或者数据热点、缓存服务宕机等原因，可能会导致缓存数据同一
个时间点大规模不可用，或者都更新。所以，需要我们的更新策略要在时间上合适，数据要均匀分
散，缓存服务器要多台高可用。
解决办法：
1、更新策略在时间上做到比较均匀。 2、使用的热数据尽量分散到不同的机器上。
3、多台机器做主从复制或者多副本，实现高可用。 4、实现熔断限流机制，对系统进行负载能力控制

Redis 使用场景-6.分布式锁 *
1、获取锁--单个原子性操作
SET dlock my_random_value NX PX 30000
2、释放锁--lua脚本-保证原子性+单线程，从而具有事务性
if redis.call("get",KEYS[1]) == ARGV[1] then
return redis.call("del",KEYS[1])
else
return 0
end
关键点：原子性、互斥、超时
https://www.cnblogs.com/dudu2mama/p/11366279.html
9)分布式消息队列
Kafka 的基本概念
1. Broker：Kafka 集群包含一个或多个服务器，这种服务器被称为 broker。
2. Topic：每条发布到 Kafka 集群的消息都有一个类别，这个类别被称为 Topic。（物理上不同
   Topic 的消息分开存储，逻辑上一个 Topic 的消息虽然保存于一个或多个 broker 上，但用户
   只需指定消息的 Topic 即可生产或消费数据而不必关心数据存于何处）。
3. Partition：Partition 是物理上的概念，每个 Topic 包含一个或多个 Partition。
4. Producer：负责发布消息到 Kafka broker。
5. Consumer：消息消费者，向 Kafka broker 读取消息的客户端。
6. Consumer Group：每个 Consumer 属于一个特定的 Consumer Group（可为每个
   Consumer 指定 group name，若不指定 group name 则属于默认的 group）

生产者-确认模式
ack=0 : 只发送不管有没有写入到 broker
ack=1：写入到leader就认为成功
ack=-1/all：写入到最小的复本数则认为成功

生产者特性-同步发送
同步发送
KafkaProducer kafkaProducer = new KafkaProducer(pro);
ProducerRecord record = new ProducerRecord("topic", "key", "value");
Future future = kafkaProducer.send(record);
//同步发送方法1
Object o = future.get();
//同步发送方法2
kafkaProducer.flush();

生产者特性-异步发送
异步发送
pro.put("linger.ms", “1");
pro.put("batch.size", "10240");
KafkaProducer kafkaProducer = new KafkaProducer(pro);
ProducerRecord record = new ProducerRecord("topic", "key", "value");
Future future = kafkaProducer.send(record);
//异步发送方法1
kafkaProducer.send(record, (metadata, exception) -> {
if (exception == null) System.out.println("record = " + record);
});
//异步发送方法2
kafkaProducer.send(record)

生产者特性-顺序保证
顺序保证
pro.put("max.in.flight.requests.per.connection", “1");
KafkaProducer kafkaProducer = new KafkaProducer(pro);
ProducerRecord record = new ProducerRecord("topic", "key", "value");
Future future = kafkaProducer.send(record);
//同步发送
kafkaProducer.send(record);
kafkaProducer.flush();

生产者特性-消息可靠性传递
pro.put("enable.idempotence","true"); // 此时就会默认把acks设置为all
pro.put("transaction.id","tx0001"); //思考一下，什么是消息的事务？？？
try {
kafkaProducer.beginTransaction();
ProducerRecord record = new ProducerRecord("topic", "key", "value");
for (int i = 0; i < 100; i++) {
kafkaProducer.send(record, (metadata, exception) -> {
if (exception != null) {
kafkaProducer.abortTransaction();
throw new KafkaException(exception.getMessage() + " , data: " + record);
} }); }
kafkaProducer.commitTransaction();
} catch (Throwable e) {
kafkaProducer.abortTransaction();
}

消费者特性-Offset 同步提交
props.put("enable.auto.commit","false");
while (true) {
//拉取数据
ConsumerRecords poll = consumer.poll(Duration.ofMillis(100));
poll.forEach(o -> {
ConsumerRecord<String, String> record = (ConsumerRecord) o;
Order order = JSON.parseObject(record.value(), Order.class);
System.out.println("order = " + order);
});
consumer.commitSync();
}

消费者特性-Offset 异步提交
props.put(“enable.auto.commit","false");
while (true) {
//拉取数据
ConsumerRecords poll = consumer.poll(Duration.ofMillis(100));
poll.forEach(o -> {
ConsumerRecord<String, String> record = (ConsumerRecord) o;
Order order = JSON.parseObject(record.value(), Order.class);
System.out.println("order = " + order);
});
consumer.commitAsync();
}

消费者特性-Offset 自动提交
props.put("enable.auto.commit","true");
props.put(“auto.commit.interval.ms”,”5000");
while (true) {
//拉取数据
ConsumerRecords poll = consumer.poll(Duration.ofMillis(100));
poll.forEach(o -> {
ConsumerRecord<String, String> record = (ConsumerRecord) o;
Order order = JSON.parseObject(record.value(), Order.class);
System.out.println("order = " + order);
});
}

消费者特性-Offset Seek
props.put("enable.auto.commit","true");
//订阅topic
consumer.subscribe(Arrays.asList("demo-source"), new ConsumerRebalanceListener() {
@Override
public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
commitOffsetToDB();
}
@Override
public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
partitions.forEach(topicPartition -> consumer.seek(topicPartition,
getOffsetFromDB(topicPartition)));
}
})
while (true) {
//拉取数据
ConsumerRecords poll = consumer.poll(Duration.ofMillis(100));
poll.forEach(o -> {
ConsumerRecord<String, String> record = (ConsumerRecord) o;
processRecord(record);
saveRecordAndOffsetInDB(record, record.offset());
});
}


第一个版本-内存 Queue
1、基于内存 Queue 实现生产和消费 API（已经完成）
1）创建内存 BlockingQueue，作为底层消息存储
2）定义 Topic，支持多个 Topic
3）定义 Producer，支持 Send 消息
4）定义Consumer，支持Poll消息
代码库里，本程序已经实现。

第二个版本：自定义 Queue
2、去掉内存 Queue，设计自定义 Queue，实现消息确认和消费 offset
1）自定义内存 Message 数组模拟 Queue。 2）使用指针记录当前消息写入位置。
3）对于每个命名消费者，用指针记录消费位置。
因为数据没有真实的弹出，还在内存，容易 OOM。
不要着急，后面考虑。。。
作业相关。

第三个版本：基于 SpringMVC 实现 MQServer
3、拆分 broker 和 client（包括 producer 和 consumer） 1）将 Queue 保存到 web server 端 2）设计消息读写 API 接口，确认接口，提交 offset 接口
3）producer 和 consumer 通过 httpclient 访问 Queue
4）实现消息确认，offset 提交
5）实现 consumer 从 offset 增量拉取
从单机走向服务器模式。

第四个版本：功能完善 MQ
4、增加多种策略（各条之间没有关系，可以任意选择实现）
1）考虑实现消息过期，消息重试，消息定时投递等策略
2）考虑批量操作，包括读写，可以打包和压缩
3）考虑消息清理策略，包括定时清理，按容量清理、LRU 等 4）考虑消息持久化，存入数据库，或 WAL 日志文件，或 BookKeeper
5）考虑将 spring mvc 替换成 netty 下的 TCP 传输协议，rsocket/websocket
完全功能。内存不 OOM，持久化。
特别是走 TCP，可以真正做到 server->client，从而实现 PUSH 模式。

第五个版本：体系完善 MQ
5、对接各种技术（各条之间没有关系，可以任意选择实现）
1）考虑封装 JMS 1.1 接口规范
2）考虑实现 STOMP 消息规范
3）考虑实现消息事务机制与事务管理器
4）对接 Spring
5）对接 Camel 或 Spring Integration
6）优化内存和磁盘的使用
到这一步，可以业务系统里放心使用了


第 1 周
单调栈模板
单调队列模板
第 2 周
前缀和、数组计数代码模板
二维前缀和代码模板
差分代码模板
双指针夹逼模板
LRU Cache
第 3 周
排列递归模板
组合递归模板
递归代码模板
分治代码模板
子集递归模板
BFS 拓扑排序模板
BFS 地图类模板
DFS 无向图找环模板
第 4 周
二叉堆代码模板
二分查找代码模板
二叉搜索树代码模板
二叉搜索树插入模板
二叉搜索树删除模板
二叉搜索树后继模板
第 5 周
堆排序代码示例
归并排序代码模板
快速排序代码模板
第 7 周
Trie 树代码模板
并查集代码模板
第 8 周
Bellman-Ford 求最短路代码模板
Dijkstra 求最短路代码模板
Kruskal 求最小生成树代码模板
Floyd 求最短路代码模板
Rabin-Karp 字符串哈希模板
字符串转整数代码示例
第 9 周
KMP 字符串匹配模板
第 10 周
树状数组代码模板
线段树代码模板