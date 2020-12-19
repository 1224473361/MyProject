package com.xhx.steam.transfer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * lambda操作list
 * 
 * @author xhx
 *
 */
public class TransferListDemo {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Entity {
		private String name;
		private String id;
		private Integer age;
		private Boolean isAdmin;
	}

	public static final Random random = new Random();

	public static void main(String[] args) {
		// 初始化测试列表
		List<String> strList = Arrays.asList("钱一", "张三", "里斯", "王五");
		List<Entity> entityList = Arrays.asList(new Entity("钱一", UUID.randomUUID().toString(), 12, true),
				new Entity("张三", UUID.randomUUID().toString(), 10, true),
				new Entity("李四", UUID.randomUUID().toString(), 13, false),
				new Entity("王五", UUID.randomUUID().toString(), 15, false),
				new Entity("赵六", UUID.randomUUID().toString(), 11, false),
				new Entity("潘七", UUID.randomUUID().toString(), 12, false),
				new Entity("杨八", UUID.randomUUID().toString(), 14, false));

		// 转换为新的列表
		System.out.println("转换为新的列表--------------------------");
		List<String> strList2 = strList.stream().map(s -> s + "-" + random.nextInt()).collect(Collectors.toList());
		strList2.forEach(System.out::println);
		List<Entity> entityList2 = entityList.stream()
				.map(s -> new Entity(s.getName(), UUID.randomUUID().toString(), s.getAge(), false))
				.collect(Collectors.toList());
		entityList2.forEach(System.out::println);
		List<Integer> entityList3 = entityList.stream().map(s -> s.getAge()).collect(Collectors.toList());
		entityList3.forEach(System.out::println);
		// 转换为数组
		System.out.println("转换为数组--------------------------");
		int[] intArr = entityList.stream().mapToInt(s -> s.getAge()).toArray();
		for (int i : intArr) {
			System.out.println(i);
		}
		String[] strArr = entityList.stream().map(s -> s.getName()).toArray(String[]::new);
		for (String string : strArr) {
			System.out.println(string);
		}
		// 转换为map
		System.out.println("转换为map--------------------------");
		Map<String, String> map1 = strList.stream()
				.collect(Collectors.toMap(s -> s, s -> UUID.randomUUID().toString()));
		map1.forEach((k, v) -> {
			System.out.println(k + "-" + v);
		});
		Map<String, Entity> map2 = entityList.stream().collect(Collectors.toMap(Entity::getId, v -> v));
		map2.forEach((k, v) -> {
			System.out.println(k + "-" + v);
		});
		// 过滤
		System.out.println("过滤--------------------------");
		Map<String, Entity> map3 = entityList.stream().filter(s -> s.getIsAdmin())
				.collect(Collectors.toMap(Entity::getId, v -> v));
		map3.forEach((k, v) -> {
			System.out.println(k + "-" + v);
		});
	}

}
