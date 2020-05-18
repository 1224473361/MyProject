package com.xhx.algorithm.sort;

import java.util.Arrays;

/**
 * <b>快速排序</b><br>
 * 
 * <p>
 * 核心思路：设立基准数
 * </p>
 * <p>
 * 时间复杂度:最差时间复杂度和冒泡排序是一样的都是O(N2)，平均时间复杂度为O(NlogN)
 * </p>
 * 
 * 参考：<a>https://www.cnblogs.com/captainad/p/10999697.html</a>
 */
public class QuickSort {

	public static <T extends Comparable> T[] quickSort(T[] arr) {
		int len = arr.length;
		if (len == 0 || len == 1) {
			return arr;
		}
		sort(arr, 0, len - 1);
		return arr;
	}

	/**
	 * 核心算法
	 * 
	 * @param <T>
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private static <T extends Comparable> T[] sort(T[] arr, int left, int right) {
		if (left > right) {
			return arr;
		}

		T base = arr[left];
		int i = left, j = right;
		while (i != j) {
			// 从右边开始查找，找到比base小的
			while (base.compareTo(arr[j]) <= 0 && i < j) {
				j--;
			}
			// 从左边开始查找，找到比base大的
			while (base.compareTo(arr[i]) >= 0 && i < j) {
				i++;
			}
			if (i < j) {
				// 交换
				T tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}

		}
		arr[left] = arr[i];
		arr[i] = base;

		sort(arr, left, i - 1);
		sort(arr, i + 1, right);
		return arr;
	}

	public static void main(String[] args) {
		Integer[] arr = { 11, 9, 5, 4, 6, 3, 10, 20, 1, 43, 25, 2 };
		System.err.println(Arrays.asList(arr));
		quickSort(arr);
		System.err.println(Arrays.asList(arr));
	}

}
