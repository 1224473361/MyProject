package com.xhx.mybatisplustest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhx.mybatisplustest.entity.User;
import com.xhx.mybatisplustest.mapper.UserMapper;
import com.xhx.mybatisplustest.service.IUserService;

/**
 * 
 * @date 2019年6月12日
 * @author lihui
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySql() {
		return baseMapper.selectListBySql();
	}

	@Override
	public List<User> selectListByWrapper(Wrapper<User> wrapper) {
		return baseMapper.selectListByWrapper(wrapper);
	}

}
