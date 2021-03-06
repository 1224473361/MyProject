package com.xhx.mybatisplustest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.xhx.mybatisplustest.entity.User;
import com.xhx.mybatisplustest.superm.SuperMapper;

/**
 * 
 * @date 2019年6月12日
 * @author xhx
 */
@Mapper
public interface UserMapper extends SuperMapper<User> {

	/**
	 * 自定义注入方法
	 */
	int deleteAll();

	@Select("select test_id as id,name,age,test_type from MUSER")
	List<User> selectListBySql();

	List<User> selectListByWrapper(@Param("ew") Wrapper<User> wrapper);

}
