package com.xhx.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/***
 * 
 * 实现功能：线程2会等着线程1结束然后才处理线程2自己的任务<br>
 * 思路：使用AtomicInteger变量，线程2会监听AtomicInteger变量的值，一旦线程1将AtomicInteger值改变为线程2预期的值则线程2开始处理自己的任务
 * 
 */
@Slf4j
public class CASDemo {

	private static final int initValue = 1;

	public static void main(String[] args) {

		AtomicInteger atomicInteger = new AtomicInteger(initValue);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				log.info("v:" + atomicInteger.get());
				try {
					TimeUnit.SECONDS.sleep(1);
					log.info("finish！");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				log.info("swap:" + atomicInteger.compareAndSet(1, 2));
				log.info("v:" + atomicInteger.get());
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					log.info("finish 2！");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				log.info("v:" + atomicInteger.get());
				StopWatch watch = new StopWatch();
				watch.start();
				do {
					///
				} while (!atomicInteger.compareAndSet(2, 3));
				watch.stop();
				log.info("swap:{},time:{}", +atomicInteger.get(), watch.getTotalTimeMillis());
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					log.info("finish！");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		});
		t1.start();
		t2.start();
	}
}
