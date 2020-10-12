## synchronized 研究
### 概述

synchronized修饰的静态方法会给整个类上加锁，被锁住的方法为该类下所有用synchronized修饰的方法

synchronized修饰的非静态方法只会给该方法加锁，其他方法不会收到影响



##### 静态方法的研究

代码在com.xhx.sync.stAtict下，程序测试入口为com.xhx.sync.stAtict.Syntest.main(String[])

- main调用Syntest的one和two，两个方法上都没有synchronized

结果为A顺序输出，b顺序输出，A尽管延迟的时间比较长，但是并不会阻塞进程

```
进入A
进入A
进入B
进入A
进入B
进入B
B执行完毕
B执行完毕
B执行完毕
A执行完毕
A执行完毕
A执行完毕
```

- main调用Syntest的one和two，one上有synchronized

结果为A顺序输出，b顺序输出，但是A只能每次只能等上次处理完毕之后才能处理该次请求

```
进入B
进入A
进入B
进入B
B执行完毕
B执行完毕
B执行完毕
A执行完毕
进入A
A执行完毕
进入A
A执行完毕
```

- main调用Syntest的one和two，one和two上有synchronized

结果为Ab交替输出：因为synchronized修饰静态方法时会为类加锁，b请求必须等A处理完之后才能处理。A也是同理。

```
进入A
A执行完毕
进入B
B执行完毕
进入A
A执行完毕
进入B
B执行完毕
进入A
A执行完毕
进入B
B执行完毕
```

- main调用Syntest的one和Syntest2的two，两个方法上都没有synchronized

结果为A顺序输出，b顺序输出，不会阻塞进程

```
Syntest-进入A 
Syntest-进入A 
Syntest-进入A 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest-A执行完毕 
Syntest-A执行完毕 
Syntest-A执行完毕 
```

- main调用Syntest的one和Syntest2的two，one上有synchronized

结果为A顺序输出，b顺序输出，但是A只能每次只能等上次处理完毕之后才能处理该次请求

```
Syntest2-进入2-B 
Syntest2-进入2-B 
Syntest-进入A 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-2-B执行完毕 
Syntest2-2-B执行完毕 
Syntest-A执行完毕 
Syntest-进入A 
Syntest-A执行完毕 
Syntest-进入A 
Syntest-A执行完毕 
```

- main调用Syntest的one和Syntest2的two，two上有synchronized

结果为A顺序输出，b顺序输出，但是B只能每次只能等上次处理完毕之后才能处理该次请求

```
Syntest2-进入2-B 
Syntest-进入A 
Syntest-进入A 
Syntest-进入A 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest-A执行完毕 
Syntest-A执行完毕 
Syntest-A执行完毕 
```

- main调用Syntest的one和Syntest2的two，one和two上有synchronized

结果为A顺序输出，b顺序输出，a和b都会阻塞自己的请求，但因为他们处两个类里面所以他们的请求互不影响

```
Syntest-进入A 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest2-进入2-B 
Syntest2-2-B执行完毕 
Syntest-A执行完毕 
Syntest-进入A 
Syntest-A执行完毕 
Syntest-进入A 
Syntest-A执行完毕
```

