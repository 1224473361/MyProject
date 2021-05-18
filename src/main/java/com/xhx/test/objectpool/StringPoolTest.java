package com.xhx.test.objectpool;

import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringPoolTest {

	public static void main(String[] args) {
		StringPoolFactory fac = new StringPoolFactory();
		GenericObjectPoolConfig<String> config = new GenericObjectPoolConfig<>();
		config.setMaxTotal(6);
		config.setMinIdle(4);
		config.setMaxWaitMillis(3000);

		try (StringPool pool = new StringPool(fac, config);) {
			for (int i = 0; i < 30; i++) {
				new Thread(() -> {
					String s = "";
					try {
						s = pool.borrowObject();
						TimeUnit.SECONDS.sleep(1);
						log.info("当前获取的字符串:{}", s);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (!s.equals("")) {
							pool.returnObject(s);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}