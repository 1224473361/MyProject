package com.xhx.contexholder.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xhx.contexholder.MyContextHolder;
import com.xhx.contexholder.detail.MyDetail;

/**
 * 自定义过滤器，用于在header里面获取参数
 */
@Component
public class MyContextHolderFilter extends OncePerRequestFilter {
	public static final String AUTHORIZATION_KEY = "authorization";
	public static final String BEARER = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String token = request.getHeader(AUTHORIZATION_KEY);
		if (token == null || token.isEmpty() || !token.contains(BEARER)) {
			chain.doFilter(request, response);
			return;
		}
		int indexOf = token.indexOf(BEARER);
		String str = token.substring(indexOf + BEARER.length());
		// 在获取到参数之后放到静态类里面
		MyContextHolder.getContext().setDetail(new MyDetail(str));
		chain.doFilter(request, response);
	}

}
