package com.xhx.autoscan.fac;

import org.springframework.beans.factory.FactoryBean;

import com.xhx.autoscan.handler.MyCglibInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年9月2日
 * @author xhx
 */
@Slf4j
public class MyAnnProxyFactory implements FactoryBean<Object> {

	private Class<?> interfaceClass;

	public MyAnnProxyFactory() {

	}

	public MyAnnProxyFactory(Class<?> interfaceClass) {
		super();
		this.interfaceClass = interfaceClass;
	}

	/**
	 * 由cglid生成代理实现类
	 * 
	 * @return
	 */
	public Object createBean() {
		Class<?> clazz = this.interfaceClass;
		log.info("加载接口类：{}", clazz);
		return MyCglibInterceptor.newProxyInstance(clazz);
	}

	@Override
	public Object getObject() throws Exception {
		return this.createBean();
	}

	@Override
	public Class<?> getObjectType() {
		return this.interfaceClass;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
