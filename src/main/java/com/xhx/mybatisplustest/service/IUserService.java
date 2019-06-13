package com.xhx.mybatisplustest.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhx.mybatisplustest.entity.User;

/**
 * 
 * @date 2019年6月12日
 * @author lihui
 */
public interface IUserService extends IService<User> {

	boolean deleteAll();
	
	public List<User> selectListBySql();
	
	public List<User> selectListByWrapper(Wrapper<User> wrapper);
}
