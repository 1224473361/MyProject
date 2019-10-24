package com.xhx.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 利用aop打印controller返回日志
 * 
 * @date 2019年5月31日
 * @author lihui
 */
@Aspect
//@Component
@Slf4j
public class WebLogAcpect {

	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.xx.*.controller..*.*(..))")
	public void webLog() {
		////
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public Object doAfterReturn(Object ret) {
		// 抓取request
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return showResult(ret, request, log);
	}

	private Object showResult(Object ajax, HttpServletRequest request, Logger log) {
		log.info("result:{}", ajax);
		log.info("URL : {}", request.getRequestURL());
		return ajax;
	}
}
