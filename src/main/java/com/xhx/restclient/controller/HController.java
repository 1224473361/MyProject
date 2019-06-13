package com.xhx.restclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhx.restclient.service.MenuSendService;

/**
 * 
 * @date 2019年5月23日
 * @author lihui
 */
@RestController
public class HController {

	@Autowired
	private MenuSendService ps;

	@RequestMapping("/h")
	public String sender(Integer c) {
		int cout = 100;
		if (null != c) {
			cout = c;
		}
		long s = System.currentTimeMillis();
		for (int i = 0; i < cout; i++) {
			Thread t = new Thread(new MyR(i));
			t.start();
		}
		long e = System.currentTimeMillis();
		System.out.println("循环执行时间：" + (e - s));
		return "s";
	}

	class MyR implements Runnable {
		int index;

		public MyR(int index) {
			super();
			this.index = index;
		}

		@Override
		public void run() {
			ps.getAllMenus();
		}

	}
}
