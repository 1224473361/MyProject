package com.xhx.concurrent.threadpool.diy.test;

import java.util.ArrayList;
import java.util.List;

import com.xhx.concurrent.threadpool.diy.entity.MyCar;
import com.xhx.concurrent.threadpool.diy.impl.CreateCarWorkshop;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年8月30日
 * @author xhx
 */
@Slf4j
public class ThreadPoolTest {

	private static Long totalCount = 100L;

	public static void main(String[] args) throws InterruptedException {
		// 创建汽车工厂
		CreateCarWorkshop carWorkshop = new CreateCarWorkshop(totalCount, 30);
		carWorkshop.setMony(123.12321);
		carWorkshop.setName("起飞");
		carWorkshop.setType(1);
		log.info("===================使用线程池创建汽车============================");
		StopWatch watch1 = new StopWatch();
		watch1.start();
		List<MyCar> rlist = carWorkshop.syncStart();
		watch1.stop();
		log.info("线程池-总共生产汽车数：{}", rlist.size());
		log.info("===================普通模式创建汽车============================");
		StopWatch watch2 = new StopWatch();
		watch2.start();
		List<MyCar> rlist2 = new ArrayList<>(totalCount.intValue());
		for (int i = 0; i < totalCount; i++) {
			try {
				MyCar car = carWorkshop.service(i);
				rlist2.add(car);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		watch2.stop();
		log.info("普通模式-总共生产汽车数：{}", rlist2.size());
		log.info("线程池-{},普通模式-{},时间差为：{}", watch1.getTotalTimeMillis(), watch2.getTotalTimeMillis(),
				(watch2.getTotalTimeMillis() - watch1.getTotalTimeMillis()));
	}

}
