package com.xhx.contexholder.strategy;

import com.xhx.contexholder.context.MyContext;

/**
 * 参数获取策略
 */
public interface MyContextStrategy {

	void clearContext();

	MyContext getContext();

	void setContext(MyContext detail);

	MyContext createEmptyContext();
}
