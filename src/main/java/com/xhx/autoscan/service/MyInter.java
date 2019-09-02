package com.xhx.autoscan.service;

import com.xhx.autoscan.annotaion.MyAnn;
import com.xhx.autoscan.annotaion.MyAnnMethod;

/**
 * 
 * @date 2019年7月2日
 * @author lihui
 */
@MyAnn
public interface MyInter {

	@MyAnnMethod(value = "为什么要写实现呢？？哈")
	public void show();
}
