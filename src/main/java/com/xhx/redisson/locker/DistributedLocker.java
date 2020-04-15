package com.xhx.redisson.locker;

import com.xhx.redisson.worker.AquiredLockWorker;

/**
 * redisson管理类
 * 
 * @author Administrator
 *
 */
public interface DistributedLocker {

	/**
	 * 获取锁
	 * 
	 * @param <T>
	 * @param resourceName 锁的名称
	 * @param worker       获取锁后 的处理类
	 * @return 处理完具体的业务逻辑要返回的数据
	 * @throws Exception
	 */
	<T> T lock(AquiredLockWorker<T> worker, String resourceName);

	/**
	 * 获取多重锁
	 * 
	 * @param <T>
	 * @param resourceName 锁的名称
	 * @param worker       获取锁后 的处理类
	 * @return 处理完具体的业务逻辑要返回的数据
	 * @throws Exception
	 */
	<T> T multiLock(AquiredLockWorker<T> worker, String... resourceName);

}
