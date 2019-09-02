package com.xhx.autoscan.handler;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.xhx.autoscan.annotaion.MyAnnMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年7月2日
 * @author lihui
 */
@Slf4j
public class MyCglibInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		MyAnnMethod as = method.getAnnotation(MyAnnMethod.class);
		if (null == as) {
			return method.invoke(target, args);
		} else {
			log.info("输出结果：{}", as.value());
			return as.value();
		}
	}

	private MyCglibInterceptor() {

	}

	public static Object newProxyInstance(Class<?> targetInstanceClazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetInstanceClazz);
		enhancer.setCallback(new MyCglibInterceptor());
		return enhancer.create();
	}
}
