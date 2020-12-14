package com.xhx.steam.funcation;

import java.util.HashMap;
import java.util.Map;

import com.xhx.common.entity.R;
import com.xhx.steam.funcation.service.impl.GetDoubleServiceImpl;
import com.xhx.steam.funcation.service.impl.GetIntegerServiceImpl;
import com.xhx.steam.funcation.service.impl.GetStringServiceImpl;

public class MainC {

	private static GetStringServiceImpl strService = new GetStringServiceImpl();
	private static GetIntegerServiceImpl intService = new GetIntegerServiceImpl();
	private static GetDoubleServiceImpl doubleService = new GetDoubleServiceImpl();

	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<>();
		map.put("str", "大师傅地方都是111");
		map.put("int", 213);
		map.put("double", 15.064d);

		R<String> result = strService.getResult("获取字符串", map);
		System.out.println(result);
		R<Integer> iresult = intService.getResult("获取整数", map);
		System.out.println(iresult);
		R<Double> dresult = doubleService.getResult("获取浮点数", map);
		System.out.println(dresult);
		map.remove("str");
		result = strService.getResult("获取字符串2", map);
		System.out.println(result);

	}

}
