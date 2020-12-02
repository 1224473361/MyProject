package com.xhx.steam.funcation.service;

import java.util.Map;

import com.thpower.common.vo.R;
import com.xhx.steam.funcation.func.MyFuncation;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象基类
 * 
 * @author xhx
 *
 */
@Slf4j
public abstract class AbstractInterService<T> {

	/**
	 * 转换结果
	 * 
	 * @param <T>
	 * @param descn
	 * @param v
	 * @param func
	 * @param clazz
	 * @return
	 */
	protected R<T> convertResult(String descn, Map<String, Object> v, MyFuncation<T> func) {
		log.info("[{}]转换结果", descn);
		if (null == v) {
			log.info("[{}]map为空", descn);
			return R.success(null);
		}
		T data = func.apply(v);
		log.info("[{}] 结果：{}", descn, data.toString());
		return R.success(data);
	}

}
