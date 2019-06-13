package com.xhx.comparisons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xhx.common.util.EStringUtil;
import com.xhx.comparisons.adapter.OperaterAdapter;

/**
 * 运算符-工具类
 * 
 * @date 2019年5月23日
 * @author lihui
 */
public class OperateUtil {

	/**
	 * 当obj存在propName的属性，且其值满足运算条件时返回true
	 * 
	 * @param obj
	 * @param propName
	 * @param operator
	 * @param value
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static boolean doOperateByCon(Object obj, String propName, String operator, String value)
			throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		if (null == obj || EStringUtil.stringIsNull(propName) || EStringUtil.stringIsNull(operator)
				|| EStringUtil.stringIsNull(value)) {
			return false;
		}
		OperaterAdapter s = new OperaterAdapter();
		Field field = obj.getClass().getDeclaredField(propName);
		Class<?> clazz = field.getType();
		String firstLetter = field.getName().substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + field.getName().substring(1);
		Method fmethod = obj.getClass().getMethod(getter);
		Object fvalue = fmethod.invoke(obj);
		return s.doOperateDeal(operator, fvalue, value, clazz);
	}

}