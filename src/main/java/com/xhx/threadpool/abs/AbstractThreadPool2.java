package com.xhx.threadpool.abs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义线程池框架-公共抽象类(spring版)
 * 
 * @date 2019年8月30日
 * @author lihui
 */
@Slf4j
public abstract class AbstractThreadPool2<T> {

	private ThreadPoolTaskExecutor executor;

	public AbstractThreadPool2(ThreadPoolTaskExecutor executor) {
		super();
		this.executor = executor;
	}

	/**
	 * 同步执行
	 * 
	 * @param taskNum 总任务数
	 * @return
	 */
	public List<T> syncStart(int taskNum) {
		log.info("开启线程池框架");
		if (taskNum == 0) {
			log.info("当前任务数量是0,任务终止");
			return new ArrayList<>(taskNum);
		}

		long start = System.currentTimeMillis();
		// 执行任务前
		this.beforeService();
		// 返回结果
		List<T> results = Collections.synchronizedList(new ArrayList<T>(taskNum));
		// 具体总任务
		List<Future<T>> futureProcesses = Collections.synchronizedList(new ArrayList<Future<T>>(taskNum));

		for (int taskIndex = 0; taskIndex < taskNum; taskIndex++) {
			final int index = taskIndex;
			Future<T> futureProcess = this.executor.submit(new Task(index));
			futureProcesses.add(futureProcess);
		}

		for (Future<T> everyFuture : futureProcesses) {
			T each = null;
			try {
				this.beforeEachService(this.executor);
				each = everyFuture.get();
				this.afterEachService(each);
				if (null != each) {
					results.add(each);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				this.exceptionService(this.executor, each, e);
			} catch (Exception e) {
				this.exceptionService(this.executor, each, e);
			}
		}
		this.executor.shutdown();
		// 执行任务后
		this.afterService(this.executor, results);
		long end = System.currentTimeMillis();
		log.info("耗时-------------------》{}", (end - start));
		log.info("关闭线程池框架");
		return results;
	}

	/**
	 * 异步执行
	 */
	public void asyncStart(int taskNum) {
		new Thread(() -> syncStart(taskNum)).start();
	}

	/**
	 * 执行任务前（会占用主线程）
	 * 
	 */
	public abstract void beforeService();

	/**
	 * 每个任务执行前（会占用主线程，耗时的处理可以放到service里面）
	 * 
	 * @param executor1
	 * @throws Exception
	 */
	public abstract void beforeEachService(ThreadPoolTaskExecutor executor1) throws Exception;

	/**
	 * 具体业务（利用线程池执行，不会占用主线程）
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
	 * @param executor2
	 * @param results
	 */
	public abstract void afterService(ThreadPoolTaskExecutor executor2, List<T> results);

	/**
	 * 任务异常处理
	 * 
	 * @param executor2
	 * @param each
	 * @param ex
	 */
	public abstract void exceptionService(ThreadPoolTaskExecutor executor2, T each, Exception ex);

	/**
	 * 内部类
	 * 
	 * @date 2019年8月30日
	 * @author lihui
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
