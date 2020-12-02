package com.xhx.steam.funcation.func;

import java.util.Map;

/**
 * 自定义函数接口
 * 
 * @author xhx
 *
 */
@FunctionalInterface
public interface MyFuncation<T> {

	/**
	 * 执行方法
	 * 
	 * @param v
	 * @return
	 */
	T apply(Map<String, Object> v);
}
