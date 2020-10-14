package com.xhx.concurrent.threadpool.spring.config;

import java.util.concurrent.RejectedExecutionHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置线程池
 *
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

	/**
	 * 核心线程数
	 */
	private int corePoolSize = 200;
	/**
	 * 最大线程数
	 */
	private int maxPoolSize = 10000;
	/**
	 * 队列最大长度
	 */
	private int queueCapacity = 200;
	/**
	 * 线程池维护线程所允许的空闲时间
	 */
	private int keepAliveSeconds = 3;
	/**
	 * 线程池对拒绝任务(无线程可用)的处理策略
	 */
	private RejectedExecutionHandler rejectedExecutionHandler = new java.util.concurrent.ThreadPoolExecutor.DiscardPolicy();

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor ex = new ThreadPoolTaskExecutor();
		ex.setQueueCapacity(queueCapacity);
		ex.setCorePoolSize(corePoolSize);
		ex.setMaxPoolSize(maxPoolSize);
		ex.setKeepAliveSeconds(keepAliveSeconds);
		ex.setRejectedExecutionHandler(rejectedExecutionHandler);
		return ex;
	}

}
