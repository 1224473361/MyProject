package com.xhx.aysnc.future.task;

import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 异步任务业务类
 * 
 * @author Administrator
 *
 */
@Component
@Async
@Slf4j
public class AysncDemoTask {

	public Future<String> task1() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(2000L);
		long end = System.currentTimeMillis();
		log.info("任务1耗时：{}", end - start);
		String res = "任务1-" + UUID.randomUUID();
		return new AsyncResult<>(res);
	}

	public Future<String> task2() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(2000L);
		long end = System.currentTimeMillis();
		log.info("任务2耗时：{}", end - start);
		String res = "任务2-" + UUID.randomUUID();
		return new AsyncResult<>(res);
	}

	public Future<String> task3() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(2000L);
		long end = System.currentTimeMillis();
		log.info("任务3耗时：{}", end - start);
		String res = "任务3-" + UUID.randomUUID();
		return new AsyncResult<>(res);
	}

}
