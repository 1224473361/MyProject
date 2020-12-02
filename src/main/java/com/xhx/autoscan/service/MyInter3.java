package com.xhx.autoscan.service;

import com.xhx.autoscan.annotaion.MyAnnMethod;

/**
 * 
 * @date 2019年7月2日
 * @author xhx
 */
public interface MyInter3 {

	@MyAnnMethod(value = "为什么要写实现呢？？哈")
	public void show();
}
