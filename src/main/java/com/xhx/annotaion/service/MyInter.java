package com.xhx.annotaion.service;

import com.xhx.annotaion.annotaion.MyAnn;

/**
 * 
 * @date 2019年7月2日
 * @author xhx
 */
public interface MyInter {

	@MyAnn(value = "为什么要写实现呢？？哈")
	public void show();
}
