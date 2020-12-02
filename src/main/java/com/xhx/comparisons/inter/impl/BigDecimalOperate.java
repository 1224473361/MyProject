package com.xhx.comparisons.inter.impl;

import java.math.BigDecimal;

import com.xhx.comparisons.inter.OperateInterface;

/**
 * 
 * @date 2019年5月23日
 * @author xhx
 */
public class BigDecimalOperate implements OperateInterface {

	@Override
	public boolean greaterDeal(Object value, String toValue) {
		// a.compareTo(b)
		// 返回值 -1 小于 0 等于 1 大于
		BigDecimal a = (BigDecimal) value;
		BigDecimal b = new BigDecimal(toValue);
		if (a.compareTo(b) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean lessDeal(Object value, String toValue) {
		BigDecimal a = (BigDecimal) value;
		BigDecimal b = new BigDecimal(toValue);
		if (a.compareTo(b) < 0) {
			return true;
		}
		return false;
	}

	@Override
	public Class<?> getRelateClass() {
		return BigDecimal.class;
	}

}
