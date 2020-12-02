package com.xhx.test.string;

import java.util.Calendar;

/**
 * 
 * @date 2019年8月26日
 * @author xhx
 */
public class StringTest {
	public static void main(String[] args) {
		// --------------数字补0-------------------
		// 0 代表前面补充0
		// 2代表长度为2
		// d 代表参数为正数型
		// 自动补零
		String str = String.format("%02d", 0);
		System.out.println(str);
		System.out.println(String.format("%04d", 123));
		System.out.println(String.format("100的一半是：%d", 100 / 2));
		System.out.println(String.format("一本书的价格是：%.2f元", 49.8));
		System.out.println(String.format("格式参数$的使用：%2$s,%1$d", 99, "abc"));

		// --------------日期-------------------
		Calendar c = Calendar.getInstance();
		System.out.println(String.format("Duke's Birthday: %1$tm %<te,%<tY", c));
		System.out.println(String.format("Duke's Birthday: %1$tY - %<te - %<tm", c));

	}

}
