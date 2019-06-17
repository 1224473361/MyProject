package com.xhx.steam.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @date 2019年6月17日
 * @author lihui
 */
public class MainC {

	public static void main(String[] args) {

		Map<String, Object> m = new HashMap<>();
		m.computeIfAbsent("a", MainC::getObject);
		m.computeIfAbsent("b", a -> {
			return a + "[]pp";
		});
		m.forEach((a, b) -> System.out.println(a + "---" + b));
		System.out.println("[c]的值：" + m.getOrDefault("c", "值不存在"));

		// 覆盖key的值，并返回覆盖之前的值
		m.put("d", "dvd");
		// 覆盖key的值，并返回覆盖之后的值
		m.compute("d", (a, b) -> {
			System.out.println(a);
			System.out.println(b);
			return b + "[]pp";
		});
		m.forEach((a, b) -> System.out.println(a + "---" + b));
	}

	public static Object getObject(Object o) {
		return o + "[]pp";
	}
}
