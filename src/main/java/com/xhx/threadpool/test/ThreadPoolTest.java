package com.xhx.threadpool.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.xhx.threadpool.entity.MyCar;
import com.xhx.threadpool.impl.CreateCarWorkshop;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年8月30日
 * @author lihui
 */
@Slf4j
public class ThreadPoolTest {

	private static Long totalCount = 100L;

	public static void main(String[] args) throws InterruptedException {

		log.info("===================使用线程池创建汽车============================");
		CreateCarWorkshop carWorkshop = new CreateCarWorkshop(totalCount, 10);
		carWorkshop.setMony(123.12321);
		carWorkshop.setName("起飞");
		carWorkshop.setType(1);
		List<MyCar> rlist = carWorkshop.syncStart();
		log.info("总共生产汽车数：{}", rlist.size());
		rlist.stream().forEach(System.out::println);
		log.info("===================普通模式创建汽车============================");
		List<MyCar> rlist2 = new ArrayList<>(totalCount.intValue());
		long start = System.currentTimeMillis();
		for (int i = 0; i < totalCount; i++) {
			MyCar car = new MyCar();
			car.setCreateTime(new Date());
			car.setId(i);
			car.setMony(123.12321);
			car.setNumb(UUID.randomUUID().toString());
			car.setType(1);
			car.setName("起飞");
			rlist2.add(car);
			//Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		log.info("耗时-------------------》{}", (end - start) / 1000);
		log.info("总共生产汽车数：{}", rlist2.size());
		//rlist2.stream().forEach(System.out::println);
	}

}
