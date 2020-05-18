package com.xhx.algorithm.sort;

import java.util.Arrays;

/**
 * <b>插入排序</b><br>
 * <br>
 * 核心思想就是将arr[i]放比他大的元素前面<br>
 * <br>
 * <p>
 * 时间复杂度是 O(N^2)，最好情景的是排序已经排好的，那就是 O（N） ，<br>
 * 因为满足不了循环的判断条件；最极端的是反序的数组，那就是<br>
 * O(N^2)。所以该算法的时间复杂度为 O(N^2)<br>
 * </p>
 * 参考:<a>https://www.toutiao.com/a6761373478785384968/</a>
 * 
 */
public class InsertSort {

	public static <T extends Comparable> void insertSort(T[] arr) {
		int j;
		for (int i = 1; i < arr.length; i++) {
			T tmp = arr[i];
			System.out.println(">>>" + i);
			for (j = i; (j > 0) && (tmp.compareTo(arr[j - 1]) < 0); j--) {
				arr[j] = arr[j - 1];
				System.out.println("---" + j);
				System.out.println(Arrays.asList(arr));
			}
			arr[j] = tmp;
			System.out.println("tmp" + Arrays.asList(arr));
		}
	}

	public static void main(String[] args) {

		Integer[] arr = { 10, 9, 5, 4, 6, 3 };
		System.err.println(Arrays.asList(arr));
		insertSort(arr);
		System.err.println(Arrays.asList(arr));

	}
}
