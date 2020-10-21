package com.xhx.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 验证基本封装的CAS操作会出现ABA问题，可以使用AtomicStampedReference解决这个问题
 */
public class ABADemo {
	private static AtomicInteger atomicIntege = new AtomicInteger(100);
	private static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);

	public static void main(String[] args) throws InterruptedException {
		// AtomicInteger
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 当前值100，是否符合预期100，t-》110
				atomicIntege.compareAndSet(100, 110);
				System.out.println(atomicIntege.get());
				// 当前值110，是否符合预期110，t->100
				atomicIntege.compareAndSet(110, 100);
				System.out.println(atomicIntege.get());
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 当变量从A修改为B在修改回A时，变量值等于期望值A，但是无法判断是否修改，CAS操作在ABA修改后依然成功。
				// 当前值100，是否符合预期100，t->120
				System.out.println("AtomicInteger:" + atomicIntege.compareAndSet(100, 120));
				System.out.println(atomicIntege.get());
			}
		});
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// Java提供了AtomicStampedReference和AtomicMarkableReference来解决。AtomicStampedReference通过包装[E,Integer]的元组来对对象标记版本戳stamp，对于ABA问题其解决方案是加上版本号，即在每个变量都加上一个版本号，每次改变时加1，即A
		// —> B —> A，变成1A —> 2B —> 3A。
		// AtomicStampedReference
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 让t4先获取stamp，导致预期时间戳不一致
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 预期引用：100，更新后的引用：110.预期标识getStamp（）》》getStamp（）+1
				reference.compareAndSet(100, 110, reference.getStamp(), reference.getStamp() + 1);
				reference.compareAndSet(110, 100, reference.getStamp(), reference.getStamp() + 1);

			}
		});

		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				int stamp = reference.getStamp();

				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("AtomicStampedReference:" + reference.compareAndSet(100, 120, stamp, stamp + 1));

			}

		});

		t3.start();
		t4.start();
	}

}
