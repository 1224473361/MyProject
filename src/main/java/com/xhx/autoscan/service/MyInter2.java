package com.xhx.autoscan.service;

import com.xhx.autoscan.annotaion.MyAnn;
import com.xhx.autoscan.annotaion.MyAnnMethod;

/**
 * 
 * @date 2019年7月2日
 * @author xhx
 */
@MyAnn
public interface MyInter2 {

	@MyAnnMethod(value = "为什么要写实现呢？？哈2131232")
	public void show();
}
