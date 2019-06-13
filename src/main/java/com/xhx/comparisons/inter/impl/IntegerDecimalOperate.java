package com.xhx.comparisons.inter.impl;

import com.xhx.comparisons.inter.OperateInterface;
/**
 * int比较类
 * 
 * @date 2019年5月23日
 * @author lihui
 */
public class IntegerDecimalOperate implements OperateInterface {

	@Override
	public boolean greaterDeal(Object value, String toValue) {
		Integer a = (Integer) value;
		Integer b = Integer.getInteger(toValue);
		return a > b;
	}

	@Override
	public boolean lessDeal(Object value, String toValue) {
		Integer a = (Integer) value;
		Integer b = Integer.getInteger(toValue);
		return a < b;
	}

	@Override
	public Class<?> getRelateClass() {
		return Integer.class;
	}

}
