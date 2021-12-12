1、注册
XxlJobSpringExecutor start() 执行器注册
JobRegistryHelper 30秒扫描，根据之间判断是否需要下线
2、任务调度
JobScheduleHelper 调度任务，通过时间轮算法，发送netty请求
3、执行器
启动内嵌的initEmbedServer接收netty请求，并放到队列，通过JobThread获取队列元素，执行方法