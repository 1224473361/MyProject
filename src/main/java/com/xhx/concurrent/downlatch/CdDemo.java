package com.xhx.concurrent.downlatch;

import java.util.concurrent.CountDownLatch;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * CountDownLatch 测试
 *
 */
@Slf4j
public class CdDemo {

	public static final int DELOY = 3000;

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(10);
		log.info("主线程开始");
		StopWatch watch = new StopWatch();
		watch.start();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					log.info("当前线程:{}", Thread.currentThread().getName());
					try {
						Thread.sleep(DELOY);
						latch.countDown();
						log.info("当前线程:{},计数减一,剩余：{}", Thread.currentThread().getName(),latch.getCount());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			t.start();
		}
		latch.await();
		watch.stop();
		log.info("主线程结束,耗时：{}", watch.getTotalTimeMillis());

	}
}
