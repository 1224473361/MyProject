package com.xhx.concurrent.threadpool.diy.abs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义线程池框架-公共抽象类（基础版）
 * 
 * @date 2019年8月30日
 * @author xhx
 */
@Slf4j
public abstract class AbstractThreadPool<T> {

	/**
	 * 任务数量
	 */
	private Long taskNum = 0l;
	/**
	 * 线程数量
	 */
	private Integer threadSize = 3;
	/**
	 * 任务线程池
	 */
	private ExecutorService executorPool = null;

	/**
	 * 构造方法
	 * 
	 * @param taskNum    总任务数量
	 * @param threadSize 线程数量
	 */
	public AbstractThreadPool(Long taskNum, Integer threadSize) {
		super();
		this.taskNum = taskNum;
		if (null != threadSize && threadSize != 0) {
			this.threadSize = threadSize;
		}
		executorPool = Executors.newFixedThreadPool(this.threadSize);
	}

	/**
	 * 同步执行
	 * 
	 * @throws Exception
	 */
	public List<T> syncStart() {
		log.info("开启线程池框架");
		if (this.taskNum == 0) {
			log.info("当前任务数量是0,任务终止");
			return new ArrayList<>(this.taskNum.intValue());
		}

		long start = System.currentTimeMillis();
		// 执行任务前
		this.beforeService();
		// 返回结果
		List<T> results = Collections.synchronizedList(new ArrayList<T>(this.taskNum.intValue()));
		// 具体总任务
		List<Future<T>> futureProcesses = Collections
				.synchronizedList(new ArrayList<Future<T>>(this.taskNum.intValue()));

		for (int taskIndex = 0; taskIndex < this.taskNum; taskIndex++) {
			final int index = taskIndex;
			Future<T> futureProcess = this.executorPool.submit(new Task(index));
			futureProcesses.add(futureProcess);
		}

		for (Future<T> everyFuture : futureProcesses) {
			T each = null;
			try {
				this.beforeEachService(this.executorPool);
				each = everyFuture.get();
				this.afterEachService(each);
				if (null != each) {
					results.add(each);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				this.exceptionService(this.executorPool, each, e);
			} catch (Exception e) {
				this.exceptionService(this.executorPool, each, e);
			}
		}
		this.executorPool.shutdown();
		// 执行任务后
		this.afterService(this.executorPool, results);
		long end = System.currentTimeMillis();
		log.info("耗时-------------------》{}", (end - start));
		log.info("关闭线程池框架");
		return results;
	}

	/**
	 * 异步执行
	 */
	public void asyncStart() {
		new Thread(() -> syncStart()).start();
	}

	/**
	 * 执行任务前（会占用主线程）
	 */
	public abstract void beforeService();

	/**
	 * 每个任务执行前（会占用主线程，耗时的处理可以放到service里面）
	 * 
	 * @param createPool
	 * @throws Exception
	 */
	public abstract void beforeEachService(ExecutorService createPool) throws Exception;

	/**
	 * 具体业务 （利用线程池执行，不会占用主线程）
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public abstract T service(int index) throws Exception;

	/**
	 * 每个任务执行后（会占用主线程，耗时的处理可以放到service里面）
	 * 
	 * @param each
	 * @throws Exception
	 */
	public abstract void afterEachService(T each) throws Exception;

	/**
	 * 执行任务后（会占用主线程）
	 * 
	 * @param createPool
	 * @param results
	 */
	public abstract void afterService(ExecutorService createPool, List<T> results);

	/**
	 * 任务异常处理
	 * 
	 * @param createPool
	 * @param each
	 * @param ex
	 */
	public abstract void exceptionService(ExecutorService createPool, T each, Exception ex);

	/**
	 * 内部类
	 * 
	 * @date 2019年8月30日
	 * @author xhx
	 */
	private class Task implements Callable<T> {
		private final Integer index;

		public Task(Integer index) {
			this.index = index;
		}

		@Override
		public T call() throws Exception {
			return service(this.index);
		}

	}
}
