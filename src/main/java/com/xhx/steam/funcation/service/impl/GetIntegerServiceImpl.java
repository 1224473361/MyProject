package com.xhx.steam.funcation.service.impl;

import java.util.Map;

import com.xhx.common.entity.R;
import com.xhx.steam.funcation.service.AbstractInterService;
import com.xhx.steam.funcation.service.InterService;

/**
 * lambda表达式方式-静态方法
 * 
 * @author xhx
 *
 */
public class GetIntegerServiceImpl extends AbstractInterService<Integer> implements InterService<Integer> {

	@Override
	public R<Integer> getResult(String descn, Map<String, Object> v) {
		return this.convertResult(descn, v, GetIntegerServiceImpl::apply);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	private static Integer apply(Map<String, Object> map) {
		return (Integer) map.getOrDefault("int", 0);
	}

}
