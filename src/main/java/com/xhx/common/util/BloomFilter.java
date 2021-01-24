package com.xhx.common.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * 布隆过滤器
 */
public class BloomFilter {
	/**
	 * 一个长度为10亿的比特位
	 */
	// private static final int DEFAULT_SIZE = 256 << 22;
	private static final int DEFAULT_SIZE = 1_0000_0000;

	/**
	 * 为了降低错误率，使用加法hash算法，所以定义一个8个元素的质数数组
	 */
	private static final int[] seeds = { 3, 5, 7, 11, 13, 31, 37, 61 };

	/**
	 * 相当于构建8个不同的hash算法
	 */
	private static HashFunction[] functions = new HashFunction[seeds.length];

	/**
	 * 初始化布隆过滤器的bitmap
	 */
	private static BitSet bitset = new BitSet(DEFAULT_SIZE);

	// private static List<String> list = new ArrayList<>(DEFAULT_SIZE);

	// private static String[] arr = new String[DEFAULT_SIZE];

	/**
	 * 添加数据
	 * 
	 * @param value
	 */
	public static void add(int index, String value) {
		if (value != null) {
			for (HashFunction f : functions) {
				// 计算hash值并修改bitmap中相应位置为true
				bitset.set(f.hash(value), true);
			}
			// arr[index] = value;
			// list.add(value);
		}
	}

	/**
	 * 布隆算法
	 * 
	 * @param value
	 * @return
	 */
	public static boolean bloomContains(String value) {
		if (null == value) {
			return false;
		}
		boolean ret = true;
		for (HashFunction f : functions) {
			ret = bitset.get(f.hash(value));
			// 一个hash函数返回false则跳出循环
			if (!ret) {
				break;
			}
		}
		return ret;
	}

	/**
	 * list
	 * 
	 * @param value
	 * @return
	 */
	public static boolean listContaion(String value) {
		if (null == value) {
			return false;
		}
		// return list.contains(value);
		return false;
	}

	/**
	 * 数组
	 * 
	 * @param value
	 * @return
	 */
	public static boolean arrContaion(String value) {
		if (null == value) {
			return false;
		}
//		for (int i = 0; i < arr.length; i++) {
//			if (arr[i].equals(value)) {
//				return true;
//			}
//		}
		return false;
	}

	public static void main(String[] args) {
		long s1 = System.currentTimeMillis();
		for (int i = 0; i < seeds.length; i++) {
			functions[i] = new HashFunction(DEFAULT_SIZE, seeds[i]);
		}
		long s2 = System.currentTimeMillis();
		time(s1, s2, "hash算法加载");
		// 添加1亿数据
		for (int i = 0; i < DEFAULT_SIZE; i++) {
			add(i, String.valueOf(i));
		}
		long s3 = System.currentTimeMillis();
		time(s2, s3, "数据加载");

		// calc("list", "-1", BloomFilter::listContaion);
		calc("bloom", "-1", BloomFilter::bloomContains);
		// calc("arr", "-1", BloomFilter::arrContaion);

		// calc("list", "312", BloomFilter::listContaion);
		calc("bloom", "312", BloomFilter::bloomContains);
		// calc("arr", "312", BloomFilter::arrContaion);

		// calc("list", "12324", BloomFilter::listContaion);
		calc("bloom", "12324", BloomFilter::bloomContains);
		// calc("arr", "12324", BloomFilter::arrContaion);

		// calc("list", "988889", BloomFilter::listContaion);
		calc("bloom", "988889", BloomFilter::bloomContains);
		// calc("arr", "988889", BloomFilter::arrContaion);

	}

	public static void calc(String msg, String value, Calc action) {
		long s1 = System.currentTimeMillis();
		boolean f = action.calc(value);
		long s2 = System.currentTimeMillis();
		time(s1, s2, "【" + msg + "】搜索》" + value + ",结果：" + f);
	}

	@FunctionalInterface
	interface Calc {
		boolean calc(String v);
	}

	public static void time(long t1, long t2, String msg) {
		System.out.println(msg + " 耗时：" + (t2 - t1));
	}

	/**
	 */
	static class HashFunction {

		private int size;
		private int seed;

		public HashFunction(int size, int seed) {
			super();
			this.size = size;
			this.seed = seed;
		}

		public int hash(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			int f = (size - 1) & result;
			return (size - 1) & result;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getSeed() {
			return seed;
		}

		public void setSeed(int seed) {
			this.seed = seed;
		}

	}

}
