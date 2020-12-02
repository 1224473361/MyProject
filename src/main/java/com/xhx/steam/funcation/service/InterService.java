package com.xhx.steam.funcation.service;

import java.util.Map;

import com.thpower.common.vo.R;

/**
 * 获取map中的结果
 * 
 * @author xhx
 *
 */
public interface InterService<T> {

	/**
	 * 获取结果
	 * 
	 * @param descn
	 * @param v
	 * @return
	 */
	R<T> getResult(String descn, Map<String, Object> v);

}
