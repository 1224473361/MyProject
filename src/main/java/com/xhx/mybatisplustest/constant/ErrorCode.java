package com.xhx.mybatisplustest.constant;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * 
 * @date 2019年6月12日
 * @author xhx
 */
public enum ErrorCode implements IErrorCode {
	TEST(1000, "测试错误编码");

	private long code;
	private String msg;

	ErrorCode(final long code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public long getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
