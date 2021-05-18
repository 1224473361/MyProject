package com.xhx.test.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * 字符串工厂
 * 
 * @author xhx
 *
 */
@Slf4j
public class StringPoolFactory extends BasePooledObjectFactory<String> {

	public StringPoolFactory() {
		super();
	}

	@Override
	public String create() throws Exception {
		Thread thread = Thread.currentThread();
		String s = "Val-" + thread.getName() + "-" + UUID.fastUUID();
		log.info("[{}]创建", s);
		return s;
	}

	@Override
	public PooledObject<String> wrap(String s) {
		return new DefaultPooledObject<>(s);
	}

	@Override
	public void destroyObject(PooledObject<String> p) throws Exception {
		String s = p.getObject();
		log.info("[{}]销毁", s);
	}

}
