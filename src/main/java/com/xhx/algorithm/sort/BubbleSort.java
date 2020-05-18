package com.xhx.algorithm.sort;

import java.util.Arrays;

/**
 * <b>冒泡排序</b><br>
 * 
 * <p>
 * 核心思路：相邻元素进行大小比较然后交换
 * </p>
 * <p>
 * 时间复杂度:最佳情况：T(n) = O(n)，最差情况：T(n) = O(N^2)，平均情况：T(n) = O(N^2)
 * </p>
 * 
 * 参考：<a>https://www.cnblogs.com/captainad/p/10845060.html</a>
 */
public class BubbleSort {

	public static <T extends Comparable> T[] bubbleSort(T[] arr) {
		int len = arr.length;
		if (len == 0 || len == 1) {
			return arr;
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0, subLen = len - i - 1; j < subLen; j++) {
				if (arr[j + 1].compareTo(arr[j]) < 0) {
					T tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
			System.out.println(Arrays.asList(arr));
		}
		return arr;
	}

	public static void main(String[] args) {
		Integer[] arr = { 10, 9, 5, 4, 6, 3 };
		System.err.println(Arrays.asList(arr));
		bubbleSort(arr);
		System.err.println(Arrays.asList(arr));
	}

}
