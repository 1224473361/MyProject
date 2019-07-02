package com.xhx.annotaion.handler;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.xhx.annotaion.annotaion.MyAnn;
import com.xhx.annotaion.service.MyInter;

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
		String me = "show";
		if (me.equals(method.getName())) {
			MyAnn as = method.getAnnotation(MyAnn.class);
			log.info("输出结果：{}", as.value());
			return as.value();
		} else {
			return method.invoke(target, args);
		}
	}

	private MyCglibInterceptor() {

	}

	public static <T extends MyInter> MyInter newProxyInstance(Class<T> targetInstanceClazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetInstanceClazz);
		enhancer.setCallback(new MyCglibInterceptor());
		return (MyInter) enhancer.create();
	}
}
