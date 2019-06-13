package com.xhx.comparisons.inter;

/**
 * 比较接口
 * 
 * @date 2019年5月23日
 * @author lihui
 */
public interface OperateInterface {

	/**
	 * 获取实现类对应的class
	 * 
	 * @return
	 */
	Class<?> getRelateClass();

	/**
	 * 等于处理
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	default boolean equalDeal(Object value, String toValue) {
		String str = value.toString();
		return str.equals(toValue);
	}

	/**
	 * 不等于处理
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	default boolean notEqualDeal(Object value, String toValue) {
		return !equalDeal(value, toValue);
	}

	/**
	 * 大于
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	boolean greaterDeal(Object value, String toValue);

	/**
	 * 大于等于处理
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	default boolean greaterEqualDeal(Object value, String toValue) {
		return !lessDeal(value, toValue);
	}

	/**
	 * 小于
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	boolean lessDeal(Object value, String toValue);

	/**
	 * 小于等于处理
	 * 
	 * @param value
	 * @param toValue
	 * @return
	 */
	default boolean lessEqualDeal(Object value, String toValue) {
		return !greaterDeal(value, toValue);
	}

}
