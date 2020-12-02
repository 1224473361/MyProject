package com.xhx.mybatisplustest.config;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局rest异常处理
 * 
 * @date 2019年6月12日
 * @author xhx
 */
@RestController
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 自定义rest业务异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public R<Object> handlerBadRequest(Exception e) {
		/**
		 * 业务异常
		 */
		if (e instanceof ApiException) {
			IErrorCode errorCode = ((ApiException) e).getErrorCode();
			if (null != errorCode) {
				log.error("rest request error,{}", errorCode.toString());
				return R.failed(errorCode);
			}
			log.debug("rest reuest error,{}", e.getMessage());
			return R.failed(e.getMessage());
		}

		/**
		 * 参数校验异常
		 */
		if (e instanceof BindException) {
			BindingResult bindingResult = ((BindException) e).getBindingResult();
			if (null != bindingResult && bindingResult.hasErrors()) {
				List<Object> jsonList = new ArrayList<>();
				bindingResult.getFieldErrors().stream().forEach(fieldError -> {
					Map<String, Object> jsonObject = new HashMap<>(2);
					jsonObject.put("name", fieldError.getField());
					jsonObject.put("msg", fieldError.getDefaultMessage());
					jsonList.add(jsonObject);
				});
				return R.restResult(jsonList, ApiErrorCode.FAILED);
			}

		}
		/**
		 * 系统内部异常，打印异常栈
		 */
		log.error("error: handleBadRequest StackTrace:{}", e);
		return R.failed(ApiErrorCode.FAILED);

	}

}
