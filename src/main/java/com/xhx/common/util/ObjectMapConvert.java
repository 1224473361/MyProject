package com.xhx.common.util;

import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

/**
 * 对象和map互转
 * 
 * @date 2019年6月27日
 * @author lihui
 */
public class ObjectMapConvert {

	/**
	 * 对象转map
	 * 
	 * @param <T>
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = ECollectionUtil.getMap();
		if (null == bean) {
			return map;
		}
		BeanMap beanMap = BeanMap.create(bean);
		for (Object key : beanMap.entrySet()) {
			map.put(key + "", beanMap.get(key));
		}
		return map;
	}

	/**
	 * map转对象
	 * 
	 * @param <T>
	 * @param map
	 * @param bean
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	/**
	 * 将List<T>转换为List<Map<String,Object>>
	 * 
	 * @param <T>
	 * @param objects
	 * @return
	 */
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objects) {
		List<Map<String, Object>> list = ECollectionUtil.getList();
		if (ECollectionUtil.listIsNull(objects)) {
			return list;
		}
		Map<String, Object> map = null;
		for (T bean : objects) {
			map = beanToMap(bean);
			list.add(map);
		}
		return list;
	}

	/**
	 * 将List<Map<String,Object>>转换为List<T>
	 * 
	 * @param <T>
	 * @param maps
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		List<T> list = ECollectionUtil.getList();
		if (ECollectionUtil.listIsNull(maps)) {
			return list;
		}
		T bean = null;
		for (Map<String, Object> map : maps) {
			bean = clazz.newInstance();
			mapToBean(map, bean);
			list.add(bean);
		}
		return list;
	}

}