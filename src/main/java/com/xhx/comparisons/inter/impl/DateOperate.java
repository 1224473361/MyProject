package com.xhx.comparisons.inter.impl;

import java.util.Date;
import com.xhx.comparisons.inter.OperateInterface;

/**
 * 日期比较类-暂未完善
 * @date 2019年5月23日
 * @author xhx
 */
public class DateOperate implements OperateInterface {

	@Override
	public boolean greaterDeal(Object value, String toValue) {
		return false;
	}

	@Override
	public boolean lessDeal(Object value, String toValue) {
		return false;
	}

	@Override
	public Class<?> getRelateClass() {
		return Date.class;
	}

}
