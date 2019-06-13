package com.xhx.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用web请求异常处理
 * 
 * @date 2019年5月5日
 * @auto lihui
 */
@ControllerAdvice
@Slf4j
public class CExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public void handle(Exception e) {
		ServletRequestAttributes aa = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = aa.getRequest();
		log.info("错误URL：{}", request.getRequestURL());
		log.info("异常信息：{}", e.getMessage());
		log.info("异常详情：{}", e);
	}

}
