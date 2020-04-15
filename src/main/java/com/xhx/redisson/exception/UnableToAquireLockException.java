package com.xhx.redisson.exception;

/**
 * redisson分布式锁-自定义异常
 * 
 * @author xhx
 *
 */
public class UnableToAquireLockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2775921068788210379L;

	public UnableToAquireLockException() {
	}

	public UnableToAquireLockException(String message) {
		super(message);
	}

	public UnableToAquireLockException(String message, Throwable cause) {
		super(message, cause);
	}

}
