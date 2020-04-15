package com.xhx.redisson.locker.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xhx.redisson.exception.UnableToAquireLockException;
import com.xhx.redisson.locker.DistributedLocker;
import com.xhx.redisson.worker.AquiredLockWorker;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisLocker implements DistributedLocker {

	private static final String LOCKER_PERFIX = "lock:";

	/**
	 * 获取锁最长等待时间
	 */
	@Value(value = "${redisson.waitTime}")
	private Integer waitTime;

	/**
	 * 获取锁最长执行时间
	 */
	@Value(value = "${redisson.leaseTime}")
	private Integer leaseTime;

	@Autowired
	private RedissonClient redissonClient;

	@Override
	public <T> T lock(AquiredLockWorker<T> worker, String resourceName) {
		RLock lock = this.redissonClient.getLock(LOCKER_PERFIX + resourceName);
		return tryLock(resourceName, worker, lock);
	}

	@Override
	public <T> T multiLock(AquiredLockWorker<T> worker, String... resourceNames) {
		List<String> asList = Arrays.asList(resourceNames);
		List<RLock> locks = new ArrayList<>(resourceNames.length);
		for (String resourceName : resourceNames) {
			RLock lock = this.redissonClient.getLock(LOCKER_PERFIX + resourceName);
			locks.add(lock);
		}
		RLock[] arr = new RLock[resourceNames.length];
		arr = locks.toArray(arr);
		RedissonMultiLock lock = new RedissonMultiLock(arr);
		return tryLock(asList, worker, lock);
	}

	private <T> T tryLock(Object descn, AquiredLockWorker<T> worker, RLock lock) {
		log.info("【{}】申请获取分布式锁", descn);
		boolean flag = false;
		try {
			flag = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
			log.info("【{}】没有获得到锁", descn);
			throw new UnableToAquireLockException();
		}
		if (flag) {
			try {
				log.info("【{}】获得到分布式锁，接下来进行业务处理", descn);
				T res = worker.invokeAfterLockAquire();
				log.info("【{}】业务处理完毕", descn);
				return res;
			} finally {
				try {
					lock.unlock();
				} catch (Exception e) {
					log.error("redisson解锁失败-" + e.getMessage());
				}
			}
		}
		log.info("【{}】没有获得到锁", descn);
		throw new UnableToAquireLockException();
	}

}
