package com.xhx.steam.funcation.service.impl;

import java.util.Map;

import com.thpower.common.vo.R;
import com.xhx.steam.funcation.func.MyFuncation;
import com.xhx.steam.funcation.service.AbstractInterService;
import com.xhx.steam.funcation.service.InterService;

/**
 * 用类实现方式获取数据
 * 
 * @author xhx
 *
 */
public class GetDoubleServiceImpl extends AbstractInterService<Double> implements InterService<Double> {

	private static DoubleFuncation instant = new DoubleFuncation();

	@Override
	public R<Double> getResult(String descn, Map<String, Object> v) {
		return this.convertResult(descn, v, instant);
	}

	static class DoubleFuncation implements MyFuncation<Double> {

		@Override
		public Double apply(Map<String, Object> map) {
			return (Double) map.getOrDefault("double", 0.0d);
		}

	}

}
