package com.xhx.sync.statict;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Syntest2 {

	/**
	 * 2-B处
	 */
	public static synchronized void two() {
		log.info("进入2-B");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("2-B执行完毕");
	}

}
