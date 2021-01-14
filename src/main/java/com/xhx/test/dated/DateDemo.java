package com.xhx.test.dated;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

/**
 * 时间api研究
 *
 */
public class DateDemo {

	public static final long ONE_DAY_SECOND = 24 * 60 * 60;

	public static void main(String[] args) {
		dateTimeFormer();
	}

	/**
	 * 格式化和解析
	 */
	public static void dateTimeFormer() {
		LocalDateTime now = LocalDateTime.now();
		String format = DateTimeFormatter.ISO_DATE.format(now);
		System.out.println(format);
		format = DateTimeFormatter.ISO_DATE_TIME.format(now);
		System.out.println(format);
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(pattern.format(now));
		
		System.err.println("parse");
		LocalDate parse = LocalDate.parse("2021-06-01");
		System.out.println(parse);
		LocalDateTime parse2 = LocalDateTime.parse("2021-01-10 22:37:07",
				pattern
				);
		System.out.println(parse2);
		
	}

	/**
	 * 本地时间
	 */
	private static void localTime() {
		LocalTime now = LocalTime.now();
		System.out.println(now);
		LocalTime time = LocalTime.of(22, 24, 59).plusHours(7);
		System.out.println(time);
		now = now.withHour(11);
		System.out.println(now);
	}

	/**
	 * 日期调整器
	 */
	public static void temporalAdjust() {
		LocalDate date = LocalDate.of(2021, Month.FEBRUARY, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
		System.out.println("2021年2月第一个周二:" + date);
		date = LocalDate.of(2021, Month.FEBRUARY, 1).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.println("2021年2月1号后第一个周一:" + date);

		TemporalAdjuster NEXT_WORKDAY = w -> {
			LocalDate res = (LocalDate) w;
			do {
				res = res.plusDays(1);
			} while (res.getDayOfWeek().getValue() >= 6);
			return res;
		};
		TemporalAdjuster NEXT_WORKDAY2 = TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate res = w;
			do {
				res = res.plusDays(1);
			} while (res.getDayOfWeek().getValue() >= 6);
			return res;
		});

		date = LocalDate.of(2021, Month.FEBRUARY, 1).with(NEXT_WORKDAY);
		System.out.println("2021年2月1号后第一个工作日:" + date);
		date = LocalDate.of(2021, Month.FEBRUARY, 1).with(NEXT_WORKDAY2);
		System.out.println("2021年2月1号后第一个工作日:" + date);
	}

	/**
	 * 本地日期
	 */
	public static void localdate() {
		LocalDate today = LocalDate.now();
		System.out.println("今天：" + today.toString());
		LocalDate date = LocalDate.of(2021, 1, 10);
		System.out.println("那天：" + date.toString());
		LocalDate date2 = LocalDate.of(2021, Month.JANUARY, 10);
		System.out.println(date.equals(date2));
		System.out.println("30天：" + date.plusDays(30));
	}

	/**
	 * 时间点
	 */
	public static void instant() {
		// 当前时间点
		Instant start = Instant.now();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instant end = Instant.now();
		// 计算时间差
		Duration between = Duration.between(start, end);
		System.out.println(between.toMillis());
		System.out.println(between.toNanos());
		System.out.println(between.getSeconds());

		Instant start2 = Instant.now();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instant end2 = Instant.now();
		Duration between2 = Duration.between(start2, end2);
		// 判断时间差2是否比时间差1小
		boolean negative = between2.minus(between).isNegative();
		System.out.println(negative);
	}

}
