package com.xhx.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 * 
 * @date 2019年4月19日
 * @auto lihui
 */
public class ECollectionUtil {

	private ECollectionUtil() {
	}

	/**
	 * 创建map
	 * 
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> Map<K, V> getMap() {
		return new HashMap<>(16);
	}

	/**
	 * 创建list
	 * 
	 * @param <E>
	 * @return
	 */
	public static <E> List<E> getList() {
		return new ArrayList<>(10);
	}

	/**
	 * 判断list是否为空
	 * 
	 * @param <E>
	 * @param l
	 * @return
	 */
	public static <E> boolean listIsNull(List<E> l) {
		if (null == l || l.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断list是否不为空
	 * 
	 * @param <E>
	 * @param l
	 * @return
	 */
	public static <E> boolean listIsNotNull(List<E> l) {
		return !listIsNull(l);
	}

}
