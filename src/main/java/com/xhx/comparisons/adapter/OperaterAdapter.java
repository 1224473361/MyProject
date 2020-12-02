package com.xhx.comparisons.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.xhx.comparisons.constant.OperatorType;
import com.xhx.comparisons.inter.OperateInterface;
import com.xhx.comparisons.inter.impl.BigDecimalOperate;
import com.xhx.comparisons.inter.impl.IntegerDecimalOperate;
import com.xhx.comparisons.inter.impl.LongDecimalOperate;
import com.xhx.comparisons.inter.impl.StringDecimalOperate;

/**
 * 适配器
 * 
 * @date 2019年5月23日
 * @author xhx
 */
public class OperaterAdapter {

	List<OperateInterface> alist = new ArrayList<>();

	public OperaterAdapter() {
		super();
		this.alist.add(new IntegerDecimalOperate());
		this.alist.add(new StringDecimalOperate());
		this.alist.add(new BigDecimalOperate());
		this.alist.add(new LongDecimalOperate());
	}

	private OperateInterface getRealImpl(Class<?> clazz) {
		for (OperateInterface ab : alist) {
			if (ab.getRelateClass().equals(clazz)) {
				return ab;
			}
		}
		return null;
	}

	/**
	 * 根据类型和运算符动态匹配算法进行计算,利用反射动态指定方法并执行
	 * 
	 * @param operate
	 * @param value
	 * @param toValue
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public boolean doOperateDeal(String operate, Object value, String toValue, Class<?> clazz)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// 通过类型匹配实现类
		OperateInterface ao = getRealImpl(clazz);
		if (null == ao) {
			return false;
		}
		// 通过枚举获得方法名称
		OperatorType t = OperatorType.fromTypeName(operate);
		if (null == t) {
			return false;
		}
		// 通过反射执行方法
		Method m = ao.getClass().getMethod(t.code() + "Deal", Object.class, String.class);
		return (boolean) m.invoke(ao, value, toValue);
	}

}
