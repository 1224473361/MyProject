package com.xhx.steam.funcation.service.impl;

import java.util.Map;

import com.thpower.common.vo.R;
import com.xhx.steam.funcation.service.AbstractInterService;
import com.xhx.steam.funcation.service.InterService;


/**
 * lambda表达式方式
 * 
 * @author xhx
 *
 */
public class GetStringServiceImpl extends AbstractInterService<String> implements InterService<String> {

	@Override
	public R<String> getResult(String descn, Map<String, Object> map) {
		return this.convertResult(descn, map, v -> (String) v.getOrDefault("str", ""));
	}

}
