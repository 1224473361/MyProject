package com.xhx.concurrent.condition;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现账户余额异常变动
 * 
 * @author Administrator
 *
 */
@Slf4j
public class Bank {

	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;

	public Bank(int n, double initialBalance) {
		this.accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
		this.bankLock = new ReentrantLock();
		this.sufficientFunds = this.bankLock.newCondition();
	}

	/**
	 * 转账
	 * 
	 * @param from
	 * @param to
	 * @param amount
	 * @throws InterruptedException
	 */
	public void transfer(int from, int to, double amount) throws InterruptedException {
		if (this.bankLock.tryLock()) {
			try {
				log.info("获得到锁");
				while (this.accounts[from] < amount) {
					// 若不满足条件则阻塞线程
					this.sufficientFunds.await();
				}
				this.accounts[from] -= amount;
				log.info(String.format(" %10.2f from %d to %d", amount, from, to));
				this.accounts[to] += amount;
				log.info(String.format(" Total Balance:%10.2f%n", this.getBalance()));
				// 操作成功后通知所有线程结束阻塞
				this.sufficientFunds.signalAll();
			} finally {
				log.info("释放锁");
				this.bankLock.unlock();
			}
			return;
		}
		log.info("未获得到锁");
	}

	/**
	 * 获取账户总余额
	 * 
	 * @return
	 */
	private double getBalance() {
		this.bankLock.lock();
		try {
			double sum = 0;
			for (double d : accounts) {
				sum += d;
			}
			return sum;
		} finally {
			this.bankLock.unlock();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.accounts.length;
	}

}