package com.xhx.aysnc.pool.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置线程池
 * @date 2019年5月14日
 * @auto lihui
 */
@Configuration
@EnableAsync
public class AysncConfig {
	private int corePoolSize = 200;
	private int maxPoolSize = 10000;
	private int queueCapacity = 200;

	@Value("${appname}")
	private String appname;
	@Value("${projectname}")
	private String projectname;

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor ex = new ThreadPoolTaskExecutor();
		ex.setQueueCapacity(queueCapacity);
		ex.setCorePoolSize(corePoolSize);
		ex.setMaxPoolSize(maxPoolSize);        
		// 设置默认线程名称
        ex.setThreadNamePrefix(appname+"-"+projectname+"-");
		return ex;
	}

}
