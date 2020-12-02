package com.xhx.annotaion.test;

import com.xhx.annotaion.handler.MyCglibInterceptor;
import com.xhx.annotaion.service.MyInter;

/**
 * 
 * @date 2019年7月2日
 * @author xhx
 */
public class MainC {

	public static void main(String[] args) {
		Class<MyInter> clazz = MyInter.class;
		MyInter in = MyCglibInterceptor.newProxyInstance(clazz);
		in.show();
	}

}
