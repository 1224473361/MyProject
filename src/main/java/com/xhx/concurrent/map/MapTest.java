package com.xhx.concurrent.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
	static Random random = new Random();

	static final String KEY = "s-1";

	public static void main(String[] args) {

		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 100; i++) {
			map.put("s-" + i, Long.valueOf(random.nextInt(500)));
			// System.out.print(map.get("s-" + i) + ",");
		}
		//System.out.println(map.get(KEY));
		// 判断key是否存在，若key不存在则直接返回要加的数据。若key存在则调用long.sum完成累加
		// Long merge = map.merge(KEY, 501L, Long::sum);
		// System.out.println(merge);

		List<Integer> vals = new ArrayList<>(100);
		for (int i = 0; i < 5; i++) {
			vals.add(i + 1);
		}
		Integer[] arr = new Integer[vals.size()];
		arr = vals.toArray(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		Arrays.parallelPrefix(arr, (x, y) -> x * y);
		for (int i = 0; i < arr.length; i++) {
			System.err.println(arr[i]);
		}

	}

}
