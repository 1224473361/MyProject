package com.xhx.mathc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;

public class MathC {

	public static void main(String[] args) {
		int d = 10;
		accumulateTriangle(d);
	}

	/**
	 * 获取阶乘的结果
	 * 
	 * @param d
	 * @return
	 */
	public static BigDecimal factorial(int d) {
		BigDecimal bd = new BigDecimal(1);
		for (int i = d; i > 0; i--) {
			bd = bd.multiply(new BigDecimal(i));
		}
		return bd;
	}

	/**
	 * 累加-循环加
	 * 
	 * @param d
	 * @return
	 */
	public static BigDecimal accumulate(int d) {
		BigDecimal bd = new BigDecimal(0);
		for (int i = d; i > 0; i--) {
			bd = bd.add(new BigDecimal(i));
		}
		return bd;
	}

	/**
	 * 累加-公式
	 * 
	 * @param d
	 * @return
	 */
	public static BigDecimal accumulate2(int d) {
		BigDecimal bd = new BigDecimal(1);
		bd = bd.add(new BigDecimal(d));
		bd = bd.multiply(new BigDecimal(d)).divide(new BigDecimal(2), 0);
		return bd;
	}

	/**
	 * 打印数字三角（偶数直角三角） <br>
	 * <b> 1 <br>
	 * 2 1 <br>
	 * 4 2 1 <br>
	 * 8 4 2 1 <br>
	 * 16 8 4 2 1 <br>
	 * </b>
	 * 
	 * @param d
	 */
	public static void evenTriangle(int d) {
		for (int i = 0; i < d; i++) {
			for (int j = i; j >= 1; j--) {
				System.out.print((int) Math.pow(2, j) + "\t");
			}
			System.out.println(1);
		}
	}

	/**
	 * 打印数字三角（累加和 等边三角） <br>
	 * <b> 1 <br>
	 * 1 2 1 <br>
	 * 1 3 3 1<br>
	 * 1 4 6 4 1 <br>
	 * 1 5 10 10 5 1 <br>
	 * </b>
	 * 
	 * @param d
	 */
	public static void accumulateTriangle(int d) {
		List<List<Integer>> rlist = new ArrayList<>(d);
		rlist.add(Lists.list(1));
		for (int i = 2; i <= d; i++) {
			List<Integer> row = new ArrayList<>(i);
			row.add(1);
			if (i >= 3) {
				row.add(i - 1);
				if (i >= 4) {
					List<Integer> preList = rlist.get(i - 2);
					for (int index = 2; i - index > 1; index++) {
						int tmp = preList.get(index) + preList.get(index - 1);
						row.add(tmp);
					}
				}
			}
			row.add(1);
			rlist.add(row);
		}

		for (List<Integer> list : rlist) {
			for (Integer str : list) {
				System.out.print(str + "\t");
			}
			System.out.println();
		}

	}

}
