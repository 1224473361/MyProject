package com.xhx.steam.listt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @date 2019年6月14日
 * @author xhx
 */
public class MainC {

	public static void main(String[] args) {
		
	}

	/**
	 * 创建arryalist比较
	 */
	public static void createArrayList() {
		// 1.使用steam--------------------------------
		List<Inter> list = Stream.of(new AInter(), new BInter(), new CInter()).collect(Collectors.toList());
		list.forEach(t -> System.out.println(t.showClass()));

		System.out.println(list.getClass());
		System.out.println(list);

		// 2.传统方法-----------------------------------
		List<Inter> list2 = new ArrayList<>();
		list2.add(new AInter());
		list2.add(new BInter());
		list2.add(new CInter());

		System.out.println(list2.getClass());
		System.out.println(list2);

	}
	
	
	
	

}
