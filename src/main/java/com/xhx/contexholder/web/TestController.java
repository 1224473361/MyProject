package com.xhx.contexholder.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhx.contexholder.MyContextHolder;
import com.xhx.contexholder.context.MyContext;

/**
 * 测试
 */
@RestController
public class TestController {

	/**
	 * 从静态类里获取参数
	 * 
	 * @return
	 */
	@GetMapping("testContext")
	public MyContext getContext() {
		return MyContextHolder.getContext();
	}

	/**
	 * 
	 * 从静态类里获取参数，延迟2s
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@GetMapping("testContext2")
	public MyContext getContext2() throws InterruptedException {
		Thread.sleep(2000);
		return MyContextHolder.getContext();
	}
}
