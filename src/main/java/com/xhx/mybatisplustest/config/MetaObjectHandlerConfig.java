package com.xhx.mybatisplustest.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 
 * @date 2019年6月12日
 * @author lihui
 */
public class MetaObjectHandlerConfig implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		// 更多查看源码测试用例
		System.out.println("*************************");
		System.out.println("insert fill");
		System.out.println("*************************");

		// 测试下划线
		Object testType = getFieldValByName("testDate", metaObject);
		System.out.println("testType=" + testType);
		if (testType == null) {
			setFieldValByName("testDate", new Date(), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		System.out.println("更新方法实体填充");
	}

}
