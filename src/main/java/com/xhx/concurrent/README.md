# 并发研究

### 悲观锁与乐观锁

### 并发三要素：原子性，可见性，有序性

### 并发工具

#### 线程池(threadpool)

#### JUC（java.util.concurrent）

1.Lock 锁
2.Semaphore 信号量 基于AQS
3.CountDownLatch 计数器(减计数方式)  基于AQS
4.CyclicBarrier 计数器(加计数方式) 基于ReentrantLock（也是基于AQS）
5.ConcurrentHashMap 线程同步的hashmap实现
6.ConcurrentLinkedQueue 线程同步的队列
7.BlockingQueue 阻塞队列
8.CopyOnWriteArrayList


### disruptor 高性能队列

### guava 并发编程库

### CompletableFuture 

### CAS （Compare And Swap）

### AQS 