package com.xhx.concurrent.cas;

import java.lang.reflect.Field;

import org.apache.velocity.runtime.log.Log;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

/**
 * 利用CAS实现计数器
 *
 */
@Slf4j
public class CASCounter {

	// 计数器
	private volatile long counter = 0;
	// 地址偏移量
	private static final long offset;
	// CAS
	private static final Unsafe unsafe = getUnsafe();

	static {
		try {
			offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	/**
	 * 通过反射的方式获得Unsafe类
	 */
	public static Unsafe getUnsafe() {
		Unsafe u = null;
		try {
			Field unsfe = Unsafe.class.getDeclaredField("theUnsafe");
			unsfe.setAccessible(true);
			u = (Unsafe) unsfe.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/**
	 * 计数+1
	 */
	public void increment() {
		long before;
		do {
			before = counter;
		} while (!unsafe.compareAndSwapLong(this, offset, before, before + 1));
	}

	public long getCounter() {
		return this.counter;
	}

	// int计数器
	private static long intCounter = 0;
	// 线程数量
	private static int threadNum = 30;

	/**
	 * 测试入口
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[threadNum];
		final CASCounter counter = new CASCounter();

		for (int i = 0; i < threadNum; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 3000; j++) {
						counter.increment();
						intCounter++;
					}
				}
			});
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		log.info("CASCounter:{},intCounter:{}", counter.getCounter(), intCounter);
	}
}
