package com.xhx.completablefutrue;

import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年10月24日
 * @author lihui
 */
@Slf4j
public class TestCompleteFuture {

	public static void main(String[] args) {
		// 例子1 单线程
		//String result = CompletableFuture.supplyAsync(() -> "Hello ").thenApplyAsync(v -> v + "world").join();
		//System.out.println(result);
		// 例子2 合并
		 String result=CompletableFuture.supplyAsync(()->{
			try {
				log.info("1");
				Thread.sleep(1000);
				log.info("1e");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Hello";
		}).thenCombine(CompletableFuture.supplyAsync(()->{
			try {
				log.info("2");
				Thread.sleep(1000);
				log.info("2e");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "world";
		}),(s1,s2)->{return s1+" >>"+s2;}).join();
		System.out.println(result); 
		
		// 例子3
		/*String result = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(true) {
				throw new RuntimeException("exception test");
			}
			return "Hi Boy";
		}).exceptionally(e->{
			//异常处理
			System.out.println(e.getMessage());
			return "null";
		}).join();
		
		System.out.println(result);*/
	}

}
