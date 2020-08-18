package com.xhx.contexholder.strategy;

import com.xhx.contexholder.context.MyContext;
import com.xhx.contexholder.context.MyContextImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 参数获取策略-全局统一参数处理
 */
@Slf4j
public class GobalContextStrategy implements MyContextStrategy {

	private MyContext context;

	@Override
	public void clearContext() {
		context = null;
	}

	@Override
	public MyContext getContext() {
		log.info("当前线程：{}", Thread.currentThread().getName());
		if (context == null) {
			context = new MyContextImpl();
		}
		return context;
	}

	@Override
	public void setContext(MyContext context) {
		this.context = context;
	}

	@Override
	public MyContext createEmptyContext() {
		log.info("创建实现类");
		return new MyContextImpl();
	}

}
