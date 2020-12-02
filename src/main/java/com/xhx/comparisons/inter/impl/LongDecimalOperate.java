package com.xhx.comparisons.inter.impl;

import com.xhx.comparisons.inter.OperateInterface;
/**
 * long比较类
 * 
 * @date 2019年5月23日
 * @author xhx
 */
public class LongDecimalOperate implements OperateInterface {

	@Override
	public boolean greaterDeal(Object value, String toValue) {
		Long a = (Long) value;
		Long b = Long.getLong(toValue);
		return a > b;
	}

	@Override
	public boolean lessDeal(Object value, String toValue) {
		Long a = (Long) value;
		Long b = Long.getLong(toValue);
		return a < b;
	}

	@Override
	public Class<?> getRelateClass() {
		return LongDecimalOperate.class;
	}

}
