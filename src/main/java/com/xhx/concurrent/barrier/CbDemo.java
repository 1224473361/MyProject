package com.xhx.concurrent.barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 从打印结果可以看出，所有线程会等待全部线程到达栅栏之后才会继续执行，并且最后到达的线程会完成 Runnable 的任务。
 *
 */
public class CbDemo {

	static class TaskThread extends Thread {

		CyclicBarrier barrier;

		public TaskThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println(getName() + " 到达栅栏A");
				barrier.await();
				System.out.println(getName() + " 冲破栅栏A");

				Thread.sleep(2000);
				System.out.println(getName() + " 到达栅栏B");
				barrier.await();
				System.out.println(getName() + " 冲破栅栏B");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		int threadNum = 4;
		CyclicBarrier c = new CyclicBarrier(threadNum, new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " 完成最后任务>>>");
			}
		});

		for (int i = 0; i < threadNum; i++) {
			new TaskThread(c).start();
		}
	}
}
