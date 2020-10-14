package com.xhx.concurrent.threadpool.spring.service;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * spring线程池调用样例
 */
@Service
@Slf4j
public class ThreadPoolTest {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	public String test() throws Exception {
		Callable<String> callable = () -> {
			String res = "1";
			Thread.sleep(1000);
			return res;
		};
		try {
			FutureTask<String> task = new FutureTask<>(callable);
			this.taskExecutor.submit(task);
			return task.get();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
