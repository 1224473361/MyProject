package com.xhx.aysnc.pool.demo;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 异步定时执行任务-发送日志
 * 
 * @date 2019年5月14日
 * @auto lihui
 */
//@Component
//@EnableScheduling
@Slf4j
public class AysncDo {

	private static final String SCHEDULESTR = "00/2 * * * * ?";

	@Async
	@Scheduled(cron = SCHEDULESTR)
	public void sendI() {
		int forn = new Random().nextInt(12);
		for (int i = 0; i < forn; i++) {
			log.info(getMapString());
		}
	}

	private String getMapString() {
		int j = new Random().nextInt(100);
		return JSONObject.toJSONString(getMap(j));
	}

	private Map<String, Object> getMap(int i) {
		Map<String, Object> m = new HashMap<>();
		m.put("threadName", Thread.currentThread().getName());
		m.put("currentDate", new Date());
		m.put("descn", "上的 福克斯的辅导UFO人给你克里夫v哦性能但是佛山杜甫的南方电视？");
		m.put("num", i);
		try {
			m.put("localhost", Inet4Address.getLocalHost().getHostAddress());
			m.put("localaddress", Inet4Address.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.error(e.getMessage(), e);
		}
		return m;
	}

}
