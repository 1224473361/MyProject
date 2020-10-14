package com.xhx.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreTest {

	// 请求总数
	public static final int clientTotal = 20;
	// 可同时并发处理的线程数
	public static final int threadTotal = 6;

	public static final int deloy = 3000;

	public static void main(String[] args) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			threadPool.execute(() -> {
				try {
					// 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
					semaphore.acquire();
					resolve(count);
					// 释放一个许可，将其返回给信号量。
					semaphore.release();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				countDownLatch.countDown();
			});
		}
		// 若不使用计数器，那么主线程会直接到这步而不是等所有子线程都处理完
		countDownLatch.await();
		threadPool.shutdown();
		watch.stop();
		log.info("总耗时:{}", watch.getTotalTimeMillis());
	}

	private static void resolve(int i) throws InterruptedException {
		log.info("请求-{}，处理中。。。", i);
		Thread.sleep(deloy);
		log.info("请求-{}，处理完毕。。。", i);
	}

}
