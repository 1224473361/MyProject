/**
 * 基于redisson框架实现分布式锁<br>
 * redisson的配置和redis一样。此部分只贴出主要代码，redis配置需要在application按实际情况配置。<br>
 * 
 * spring:<br>
 * #redis连接池配置<br>
 * redis:<br>
 * timeout: 6000ms<br>
 * password: <br>
 * #单节点配置 <br>
 * host: 127.0.0.1<br>
 * port: 6379 <br>
 * lettuce:<br>
 * pool:<br>
 * max-active: 1000 #连接池最大连接数（使用负值表示没有限制）<br>
 * max-idle: 10 # 连接池中的最大空闲连接<br>
 * min-idle: 5 # 连接池中的最小空闲连接<br>
 * max-wait: -1 # 连接池最大阻塞等待时间（使用负值表示没有限制） <br>
 * 
 * @author xhx
 *
 */
package com.xhx.redisson;