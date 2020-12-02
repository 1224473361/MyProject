package com.xhx.comparisons.inter.impl;

import com.xhx.comparisons.inter.OperateInterface;
/**
 * 字符串比较类
 * 
 * @date 2019年5月23日
 * @author xhx
 */
public class StringDecimalOperate implements OperateInterface {

	@Override
	public boolean greaterDeal(Object value, String toValue) {
		if (value.toString().compareTo(toValue) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean lessDeal(Object value, String toValue) {
		if (value.toString().compareTo(toValue) < 0) {
			return true;
		}
		return false;
	}

	@Override
	public Class<?> getRelateClass() {
		return String.class;
	}

}
