package com.xhx.contexholder;

import com.xhx.contexholder.context.MyContext;
import com.xhx.contexholder.strategy.MyContextStrategy;
import com.xhx.contexholder.strategy.ThreadLocalContextStrategy;

/**
 * 参数获取静态类
 * 
 * @author xhx
 * @date 2020年8月18日
 *
 */
public class MyContextHolder {

	private static int initializeCount = 0;

	private static MyContextStrategy strategy;

	static {
		initialize();
	}

	private static void initialize() {
		strategy = new ThreadLocalContextStrategy();
		// strategy = new GobalContextStrategy();
		// strategy = new GobalContextStrategy();

		initializeCount++;
	}

	public static void setContext(MyContext context) {
		strategy.setContext(context);
	}

	public static MyContext getContext() {
		return strategy.getContext();
	}

	public static int getInitializeCount() {
		return initializeCount;
	}

}
