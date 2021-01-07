package com.xhx.steam.listt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @date 2019年6月14日
 * @author xhx
 */
public class MainC {

	public static void main(String[] args) {
		group();
	}

	/**
	 * 创建arryalist比较
	 */
	public static void createArrayList() {
		// 1.使用steam--------------------------------
		List<Inter> list = Stream.of(new AInter(), new BInter(), new CInter()).collect(Collectors.toList());
		list.forEach(t -> System.out.println(t.showClass()));

		System.out.println(list.getClass());
		System.out.println(list);

		// 2.传统方法-----------------------------------
		List<Inter> list2 = new ArrayList<>();
		list2.add(new AInter());
		list2.add(new BInter());
		list2.add(new CInter());

		System.out.println(list2.getClass());
		System.out.println(list2);

	}

	/**
	 * 流处理
	 */
	public static void stream() {
		// 子流
//		Stream<Double> s1 = Stream.generate(Math::random).limit(100);
//		s1.forEach(System.out::println);
//		Stream<Integer> s2 = Stream.of(1, 2, 4, 7, 2, 4, 1, 0).skip(5);
//		s2.forEach(System.out::println);
//		Stream<Integer> s3 = Stream.of(1, 2, 4, 7, 2, 4, 1, 0).distinct();
//		s3.forEach(System.err::println);
		// 测试方法 peek
//		System.out.println("iterate");
//		Object[] arr = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println(e)).limit(20).toArray();
//		for (Object object : arr) {
//			System.out.println(object);
//		}
		// Optional
//		List<String> words = Arrays.asList("a", "s", "c", "v", "1", "54", "52", "5fgh");
//		Optional<String> findFirst = words.stream().filter(s -> s.contains("z")).findFirst();
//		System.out.println(findFirst.orElse("None"));
//		Optional<String> empty = Optional.empty();
//		String res = empty.orElse("N/A");
//		System.out.println(res);
//		String res2 = empty.orElseGet(() -> Locale.getDefault().getDisplayName());
//		System.out.println(res2);
		// collect
//		TreeSet<String> set = words.stream().collect(Collectors.toCollection(TreeSet::new));
//		set.stream().forEach(System.out::println);
//		String str = words.stream().collect(Collectors.joining(","));
//		System.out.println(str);
//		IntSummaryStatistics summaryStatistics = words.stream().collect(Collectors.summarizingInt(String::length));
//		System.out.println(summaryStatistics.getAverage());
//		System.out.println(summaryStatistics.getSum());

//		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
//		Map<String, String> localeMap = locales.collect(Collectors.toMap(Locale::getDisplayLanguage,
//				l -> l.getDisplayLanguage(l), (existingVal, newVal) -> existingVal));
//		System.out.println(localeMap);

	}

	/**
	 * 分组
	 */
	public static void group() {
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// group
//		Map<String, List<Locale>> countrys = locales.collect(Collectors.groupingBy(Locale::getCountry));
//		List<Locale> clist = countrys.get("CH");
//		clist.forEach(System.out::println);
		// 下游收集器
//		Map<String, Long> ccMap = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
//		ccMap.forEach((k, v) -> {
//			System.out.println(k + ">" + v);
//		});

		Map<String, Set<String>> mmap = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry,
				Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
		mmap.forEach((k, v) -> {
			System.out.println(k + ">" + v);
		});
		
//		locales = Stream.of(Locale.getAvailableLocales());
		// partitioningBy
//		Map<Boolean, List<Locale>> cnMap = locales
//				.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
//		List<Locale> cnlist = cnMap.get(true);
//		cnlist.forEach(System.out::println);

	}

}
