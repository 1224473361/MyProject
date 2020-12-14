package com.xhx.common.entity;

/**
 * RESTful结果code枚举
 */
public enum RCode {

	/**
	 * success
	 */
	SUCCESS(0, "success"),
	/**
	 * fail
	 */
	FAIL(-1, "fail"),
	/**
	 * 参数ID不能为空
	 */
	PARAM_ID_ISNULL(10, "参数ID不能为空"),
	/**
	 * 数据为空
	 */
	RESULT_ISNULL(20, "数据为空"),
	/**
	 * 结果数大于一个
	 */
	RESULT_MORE_THAN_ONE(50, "结果数大于一个"),
	/**
	 * 大数据返回异常
	 */
	OTHER_BIGDATA_RETURNFALSE(60, "大数据返回异常");

	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	private RCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
