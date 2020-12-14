package com.xhx.common.entity;

/**
 * RESTful结果返回实体
 */
public class R<T> {

	private Integer code;
	private String message;
	private T data;

	/**
	 * 成功的message
	 */
	public static final String MESSAGE_SUCCESS = RCode.SUCCESS.getMsg();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

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
		return this.code.equals(RCode.SUCCESS.getCode());
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
	 * @param msg
	 * @return
	 */
	public static <T> R<T> fail(RCode code, String msg) {
		return fail(code.getCode(), msg, null);
	}

	/**
	 * 封装失败返回
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> R<T> fail(RCode code) {
		return fail(code, code.getMsg());
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

	@Override
	public String toString() {
		return "R [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
