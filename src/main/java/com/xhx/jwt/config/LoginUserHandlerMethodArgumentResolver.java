package com.xhx.jwt.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xhx.jwt.anno.LoginUser;

/**
 * 参数解析器，结合注解从header里面解析出用户id
 * 
 * @author xhx
 *
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(Integer.class)
				&& parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request,
			WebDataBinderFactory factory) throws Exception {

		String token = request.getHeader(com.xhx.jwt.constant.JwtConstant.SECRET);
		if (token == null || token.isEmpty()) {
			return null;
		}

		return UserTokenManager.getUserId(token);
	}
}
