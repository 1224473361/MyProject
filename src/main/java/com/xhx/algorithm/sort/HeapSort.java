package com.xhx.algorithm.sort;

import java.util.Arrays;

/**
 * <b>堆排序</b><br>
 * 
 * <p>
 * 核心思路：
 * </p>
 * <p>
 * 时间复杂度:
 * </p>
 * 
 * 参考：<a>https://www.jianshu.com/p/11655047ab58</a>
 * 参考：<a>https://www.cnblogs.com/CherishFX/p/4643940.html</a>
 */
public class HeapSort {

	public static <T extends Comparable> T[] heapSort(T[] arr) {
		// 初始化堆
		for (int i = (arr.length) / 2 - 1; i >= 0; i--) {
			heapAdjust(arr, arr.length, i);
		}

		System.out.println("堆顶调整");
		for (int i = arr.length - 1; i >= 1; i--) {
			T tmp = arr[0];
			arr[0] = arr[i];
			arr[i] = tmp;
			heapAdjust(arr, i, 0);
		}
		return arr;
	}

	private static <T extends Comparable> T[] heapAdjust(T[] arr, final int len, final int i) {
		int k = i;
		int index = 2 * k + 1;
		T tmp = arr[i];
		while (index < len) {
			if (index + 1 < len) {
				if (arr[index].compareTo(arr[index + 1]) < 0) {
					index++;
				}
			}
			if (arr[index].compareTo(tmp) > 0) {
				arr[k] = arr[index];
				k = index;
				index = 2 * k + 1;
			} else {
				break;
			}
		}
		arr[k] = tmp;
		System.out.println(Arrays.asList(arr)); 
		return arr;
	}

	public static void main(String[] args) {
		Integer[] arr = { 16, 7, 2, 20, 11, 8, 22 };
		System.err.println(Arrays.asList(arr));
		heapSort(arr);
		System.err.println(Arrays.asList(arr));
	}

}
