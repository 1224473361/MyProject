package com.xhx.jwt.config;

import com.xhx.jwt.util.JwtHelper;

/**
 * 维护用户token
 */
public class UserTokenManager {
	
	/**
	 * 生成token
	 * 
	 * @param id
	 * @return
	 */
	public static String generateToken(Integer id) {
		JwtHelper jwtHelper = new JwtHelper();
		return jwtHelper.createToken(id);

	}

	/**
	 * 从token中获取用户id
	 * 
	 * @param token
	 * @return
	 */
	public static Integer getUserId(String token) {
		JwtHelper jwtHelper = new JwtHelper();
		Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
		if (userId == null || userId == 0) {
			return null;
		}
		return userId;
	}
}
