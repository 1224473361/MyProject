package com.xhx.redisson.worker;

/**
 * 获取锁需要处理的逻辑
 * 
 * @author Administrator
 *
 * @param <T>
 */
@FunctionalInterface
public interface AquiredLockWorker<T> {

	T invokeAfterLockAquire();
}
