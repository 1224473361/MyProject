package com.xhx.contexholder.strategy;

import com.xhx.contexholder.context.MyContext;
import com.xhx.contexholder.context.MyContextImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 参数获取策略-按线程划分保存参数，子线程可以继承父线程数据
 *
 */
@Slf4j
public class InheritableThreadLocalContextStrategy implements MyContextStrategy {

	private static final ThreadLocal<MyContext> contextHolder = new InheritableThreadLocal<>();

	@Override
	public void clearContext() {
		contextHolder.remove();
	}

	@Override
	public MyContext getContext() {
		log.info("当前线程：{}", Thread.currentThread().getName());
		MyContext ctx = contextHolder.get();
		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}
		return ctx;
	}

	@Override
	public void setContext(MyContext context) {
		contextHolder.set(context);
	}

	@Override
	public MyContext createEmptyContext() {
		log.info("创建实现类");
		return new MyContextImpl();
	}

}
