package com.xhx.common.entity;

/**
 * 公共返回实体类
 * 
 * @date 2019年4月30日
 * @auto lihui
 */
public class CommonBackEntity<T> {

	/**
	 * 返回标识-操作结果-成功
	 */
	public static final String RETURN_FLAG_SUCCESS = "0";
	/**
	 * 返回标识-操作结果-失败
	 */
	public static final String RETURN_FLAG_FAIL = "-1";
	/**
	 * 返回标识-操作结果-错误
	 */
	public static final String RETURN_FLAG_ERROR = "-2";

	private T data;
	private String status;
	private String msg;

	/**
	 * 只返回标志位
	 * 
	 * @param status
	 */
	public CommonBackEntity(String status) {
		super();
		this.status = status;
	}

	/**
	 * 不带结果的构造
	 * 
	 * @param status
	 * @param msg
	 */
	public CommonBackEntity(String status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	/**
	 * 带结果的构造
	 * 
	 * @param status
	 * @param msg
	 * @param data
	 */
	public CommonBackEntity(String status, String msg, T data) {
		super();
		this.data = data;
		this.status = status;
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 返回实体
	 * 
	 * @param <E>
	 * @param status
	 * @param msg
	 * @return
	 */
	public static <E> CommonBackEntity<E> getStatusBack(String status, String msg) {
		return new CommonBackEntity<>(status, msg);
	}

	/**
	 * 返回实体
	 * 
	 * @param <T>
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> CommonBackEntity<T> getBack(String status, String msg, T data) {
		return new CommonBackEntity<>(status, msg, data);
	}

	@Override
	public String toString() {
		return "CommonBackEntity [data=" + data + ", status=" + status + ", msg=" + msg + "]";
	}

}
