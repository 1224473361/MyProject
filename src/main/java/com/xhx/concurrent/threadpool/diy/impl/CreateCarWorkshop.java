package com.xhx.concurrent.threadpool.diy.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.xhx.concurrent.threadpool.diy.abs.AbstractThreadPool;
import com.xhx.concurrent.threadpool.diy.entity.MyCar;

import lombok.extern.slf4j.Slf4j;

/**
 * 利用线程池生产汽车
 * 
 * @date 2019年8月30日
 * @author xhx
 */
@Slf4j
public class CreateCarWorkshop extends AbstractThreadPool<MyCar> {

	private String name;

	private Integer type;

	private Double mony;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setMony(Double mony) {
		this.mony = mony;
	}

	public CreateCarWorkshop(Long taskNum, Integer threadSize) {
		super(taskNum, threadSize);
	}

	@Override
	public void beforeService() {
		log.info("准备生产》》》》");
	}

	@Override
	public void beforeEachService(ExecutorService createPool) throws Exception {
		log.info("准备生产每辆汽车》》》》");
	}

	@Override
	public MyCar service(int index) throws Exception {
		log.info("生产每辆汽车》》》》");
		MyCar car = new MyCar();
		car.setCreateTime(new Date());
		car.setId(index);
		car.setMony(mony);
		car.setNumb(UUID.randomUUID().toString());
		car.setType(type);
		car.setName(name);
		Thread.sleep(100);
		return car;
	}

	@Override
	public void afterEachService(MyCar each) throws Exception {
		log.info("完成汽车-{}的生产》》》》", each.getName());
	}

	@Override
	public void afterService(ExecutorService createPool, List<MyCar> results) {
		log.info("出厂所有汽车》》》》");
	}

	@Override
	public void exceptionService(ExecutorService createPool, MyCar each, Exception ex) {
		log.error(ex.getMessage(), ex);
		log.info("汽车{}生产失败", each.getName());
	}

}
