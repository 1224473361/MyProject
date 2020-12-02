package com.xhx.concurrent.completablefutrue;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年10月23日
 * @author xhx
 */
@Slf4j
public class Shop {

	private Random random = new Random();

	/**
	 * 同步获取价格
	 * 
	 * @param product
	 * @return
	 */
	public double getPrice(String product) {
		log.info(product);
		return calculatePrice(product);
	}

	/**
	 * 异步计算价格
	 * 
	 * @param product
	 * @return
	 */
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			double price = calculatePrice(product);
			futurePrice.complete(price);
		}).start();
		return futurePrice;
	}

	private double calculatePrice(String product) {
		delay();
		return random.nextDouble();
	}

	public static void delay() {
		try {
			//Thread.sleep(1000);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void doOtherThings() {
		System.out.println("doOtherThings>>>>>>>>>>>>>>>>>>>");
	}

	@Test
	public void aysncTest() {
		Shop shop = new Shop();
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs>>>>>>>>>>>>>>>>>>>");

		// 做其他事情
		shop.doOtherThings();
		//
		try {
			Double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs>>>>>>>>>>>>>>>>>>>");

	}

}
