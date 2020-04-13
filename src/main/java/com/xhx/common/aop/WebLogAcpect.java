package com.xhx.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;

import lombok.extern.slf4j.Slf4j;

/**
 * 利用aop打印controller返回日志
 * 
 * @date 2019年5月31日
 * @author lihui
 */
@Aspect
// @Component
@Slf4j
public class WebLogAcpect {

	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.xx.*.controller..*.*(..))")
	public void webLog() {
		////
	}

	/**
	 * @param ret
	 * @return
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public Object doAfterReturn(Object ret) {
		// 抓取request
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return showResult(ret, request);
	}

	/**
	 * 打印http请求结果
	 * 
	 * @param ajax
	 * @param request
	 * @return
	 */
	private Object showResult(Object ajax, HttpServletRequest request) {
		/*if (ajax instanceof ResponseUtil) {
			log.info("请求URL:{},处理结果:{}", request.getRequestURL(), JSONObject.toJSONString(ajax));
		} else {
			log.info("请求URL:{},处理结果:{}", request.getRequestURL(), ajax);
		}*/
		log.info("请求URL:{},处理结果:{}", request.getRequestURL(), ajax);
		return ajax;
	}

	/**
	 * 配置前置通知
	 * 
	 * @param joinPoint
	 */
	@Before("webLog()")
	public void logBeforeAdvice(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		// 获取传入的参数
		int length = args.length;
		Object[] args2 = new Object[length];
		int index = 0;
		for (int i = 0; i < length; i++) {
			if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {
				continue;
			}
			args2[index] = args[i];
			index++;
		}
		// 获取当前被切的类
		String className = joinPoint.getTarget().getClass().getSimpleName();
		// 获取当前执行的方法
		String methodName = joinPoint.getSignature().getName();
		String json = "";
		try {
			json = JSONArray.toJSONString(args2);
		} catch (Exception e) {
			log.error("http参数日志，参数转换异常：{}", e.getMessage());
		}

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		log.info("请求URL:{},处理方法:{},请求参数:{}", request.getRequestURL(), className + "." + methodName, json);
	}

}
