package com.xhx.steam.transfer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * lambda操作list
 * 
 * @author xhx
 *
 */
public class TransferMapDemo {
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Entity {
		private String name;
		private String id;
		private Integer age;
		private Boolean isAdmin;
	}

	public static final Random random = new Random(100);

	public static void main(String[] args) {
		// 初始化测试map
		Map<String, String> strMap = new HashMap<>();
		strMap.put("钱一", Integer.toString(random.nextInt()));
		strMap.put("张三", Integer.toString(random.nextInt()));
		strMap.put("里斯", Integer.toString(random.nextInt()));
		strMap.put("王五", Integer.toString(random.nextInt()));
		Map<String, Entity> entityMap = new HashMap<>();
		entityMap.put(UUID.randomUUID().toString(), new Entity("钱一", UUID.randomUUID().toString(), 12, true));
		entityMap.put(UUID.randomUUID().toString(), new Entity("张三", UUID.randomUUID().toString(), 10, true));
		entityMap.put(UUID.randomUUID().toString(), new Entity("李四", UUID.randomUUID().toString(), 13, false));
		entityMap.put(UUID.randomUUID().toString(), new Entity("王五", UUID.randomUUID().toString(), 15, false));
		entityMap.put(UUID.randomUUID().toString(), new Entity("赵六", UUID.randomUUID().toString(), 11, false));
		entityMap.put(UUID.randomUUID().toString(), new Entity("潘七", UUID.randomUUID().toString(), 12, false));
		entityMap.put(UUID.randomUUID().toString(), new Entity("杨八", UUID.randomUUID().toString(), 14, false));

	}
}
