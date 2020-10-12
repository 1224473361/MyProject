package com.xhx.sync.statict;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Syntest {

	/**
	 * A处
	 */
	public static synchronized void one() {
		log.info("进入A");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("A执行完毕");
	}

	/**
	 * B处
	 */
	public static void two() {
		log.info("进入B");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("B执行完毕");
	}

	/**
	 * 测试入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					Syntest.one();
				}
			});
			Thread b = new Thread(new Runnable() {
				public void run() {
					Syntest2.two();
				}
			});
			a.start();
			b.start();
		}
	}

}


