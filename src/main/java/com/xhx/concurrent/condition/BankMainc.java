package com.xhx.concurrent.condition;

public class BankMainc {

	/**
	 * 账户数
	 */
	public static final int NACCOUNTS = 100;
	/**
	 * 初始账户余额
	 */
	public static final double INIT_BALANCE = 1000;
	/**
	 * 最大转账金额
	 */
	public static final double MAX_AMOUNT = 1000;

	public static final int DELAY = 10;

	public static void main(String... args) {
		Bank bank = new Bank(NACCOUNTS, INIT_BALANCE);
		for (int i = 0; i < NACCOUNTS; i++) {
			int fromAccount = i;
			Runnable r = () -> {
				try {
					while (true) {
						int toAccount = (int) (bank.size() * Math.random());
						if(fromAccount==toAccount) {
							continue;
						}
						double amount = MAX_AMOUNT * Math.random();
						bank.transfer(fromAccount, toAccount, amount);
						Thread.sleep((long) (DELAY * Math.random()));
					}
				} catch (Exception e) {

				}
			};

			Thread t = new Thread(r);
			t.start();
		}

	}

}
