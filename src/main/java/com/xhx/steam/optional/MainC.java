package com.xhx.steam.optional;

import java.util.Optional;

/**
 * 
 * @date 2019年6月20日
 * @author xhx
 */
public class MainC {
	
	public static String f="null";
	public static String s;
	
	public static String setF(String s) {
		System.out.println(s);
		MainC.s=s;
		return s;
	}
	
	public static void main(String[] args) {
		//有值时调用后面的方法
		Optional.ofNullable(f).ifPresent(MainC::setF);
		System.out.println(s);
	}
	
	
	
}
