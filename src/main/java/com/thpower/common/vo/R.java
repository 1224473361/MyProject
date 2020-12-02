package com.thpower.common.vo;

import lombok.Data;

/**
 * 返回结果集
 * 
 */
@Data
public class R<T> {

	/**
	 * 返回结果码
	 */
	private Integer code;
	/**
	 * 返回错误描述
	 */
	private String message;
	/**
	 * 返回结果
	 */
	private T data;

	/**
	 * 成功的code
	 */
	private static final Integer CODE_SUCCESS = 0;

	/**
	 * 成功的message
	 */
	private static final String MESSAGE_SUCCESS = "成功";

	public R(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 判断接口是否返回成功
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return this.code.equals(CODE_SUCCESS);
	}

	public R() {
		super();
	}

	/**
	 * 封装成功返回
	 * 
	 * @param data
	 * @return
	 */
	public static <T> R<T> success(Integer code, String message, T data) {
		return new R<>(code, message, data);
	}

	/**
	 * 封装成功返回
	 * 
	 * @param data
	 * @return
	 */
	public static <T> R<T> success(T data) {
		return new R<>(0, MESSAGE_SUCCESS, data);
	}

	/**
	 * 封装失败返回
	 * 
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static <T> R<T> fail(Integer code, String message, T data) {
		return new R<>(code, message, data);
	}

	/**
	 * 封装失败返回
	 * 
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static <T> R<T> fail(Integer code, String message) {
		return fail(code, message, null);
	}

	/**
	 * 封装失败返回
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> R<T> fail(R<?> r) {
		return fail(r.getCode(), r.getMessage(), null);
	}
}
