package com.xhx.mybatisplustest.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhx.mybatisplustest.constant.ErrorCode;
import com.xhx.mybatisplustest.entity.User;
import com.xhx.mybatisplustest.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * mybatisPlus 测试
 * 
 * @date 2019年6月12日
 * @author xhx
 */
@RestController
@Slf4j
public class UserController extends ApiController {

	@Autowired
	private IUserService userService;

	/**
	 * @param test
	 * @return
	 */
	@RequestMapping("testError")
	public R<String> testError(String test) throws Exception {
		Assert.notNull(ErrorCode.TEST, test);
		return success(test);
	}

	/**
	 * AR 部分测试
	 * 
	 * @return
	 */
	@RequestMapping("/test1")
	public IPage<User> test1() {
		User user = new User("testAew", 11, 11L);
		// log.info("删除所有:{}", user.delete(null));
		user.setRole(112L);
		// user.setTestDate(new Date());
		user.setPhone("1564");
		user.insert();
		log.info("查询插入结果:{}", user.selectById().toString());
		user.setName("mypsdfsd");
		log.info("更新:{}", user.updateById());
		return user.selectPage(new Page<User>(2, 5), null);
	}

	/**
	 * 增删该查 crud
	 * 
	 * @return
	 */
	@RequestMapping("/test2")
	public User test2() {
		log.info("删除一条数据:{}", userService.removeById(1L));
		// log.info("deleteAll:{}", userService.deleteAll());
		log.info("插入一条数据:{}", userService.save(new User(1L, "长手动", 15, 2L)));
		User user = new User("长手动", 15, 2L);
		boolean result = userService.save(user);
		// 自动回写id
		Long id = user.getId();
		log.info("插入一条数据:{},插入信息:{}", result, user.toString());
		log.info("查询:{}", userService.getById(id).toString());
		log.info("更新一条数据:{}", userService.updateById(new User(1L, "三毛", 11, 4L)));
		for (int i = 0; i < 5; i++) {
			userService.save(new User("were-" + i, 11, Long.valueOf(100L + i)));
		}
		IPage<User> ulist = userService.page(new Page<User>(1, 2), new QueryWrapper<User>());
		log.info("total:{},current list size:{}", ulist.getTotal(), ulist.getRecords().size());

		ulist = userService.page(new Page<User>(1, 5), new QueryWrapper<User>().orderByAsc("name"));
		log.info("total:{},current list size:{}", ulist.getTotal(), ulist.getRecords().size());
		return userService.getById(6642398l);
	}

	/**
	 * 插入or修改
	 * 
	 * @return
	 */
	@RequestMapping("test3")
	public User test3() {
		User user = new User(6642398L, "往往" + new Random().nextInt(), 1, 3L);
		user.setPhone("4657");
		boolean f = userService.saveOrUpdate(user);
		log.info("插入更新结果：{}", f);
		return userService.getById(user.getId());
	}

	@RequestMapping("selectSql")
	public List<User> selectSql() {
		return userService.selectListBySql();
	}

	@RequestMapping("selectWrapper")
	public List<User> selectWrapper() {
		return userService.selectListByWrapper(new QueryWrapper<User>().orderByAsc("name").orderByDesc("test_id")
				.lambda().like(User::getName, "%w%").and(e -> e.eq(User::getTestType, 5L)));
	}

	/**
	 * 参数模式分页<br>
	 * 分页<br>
	 * localhost/page?size=1&current=1<br>
	 * 集合模式，不进行分页直接返回所有结果集<br>
	 * localhost/page?listMode=true<br>
	 * 
	 * @param page
	 * @param listMode
	 * @return
	 */
	@RequestMapping("page")
	public IPage<User> page(Page<User> page, boolean listMode) {
		if (listMode) {
			// size 小于0不再查询total及分页，自动调整为列表模式。
			page.setSize(-1);
		}
		return userService.page(page, null);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping("tran")
	public void tran() {
		User user = new User("测试事务", 131, 6L);
		userService.save(user);
		log.info("手动抛出异常");
		throw new RuntimeException();
	}
	
	@RequestMapping("listMaps")
	public List<Map<String, Object>> listMaps() {
		return userService.listMaps();
	}

}
