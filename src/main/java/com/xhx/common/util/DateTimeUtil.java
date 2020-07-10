package com.xhx.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 基于jdk 1.8 日期格式化工具类
 */
public class DateTimeUtil {

	public static final String YY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HHMM_Z = "yyyy年MM月dd日 HH:mm:ss";
	public static final String PATTERN_YYYY_DD_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final DateTimeFormatter FORMAT_YYYY_DD_MM = DateTimeFormatter.ofPattern(YY_MM_DD);
	public static final DateTimeFormatter FORMAT_YYYY_DD_MM_HH_MM_SS = DateTimeFormatter
			.ofPattern(PATTERN_YYYY_DD_MM_HH_MM_SS);

	/**
	 * LocalDate转Date
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date getDateByLocalDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDateTime转Date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date getDateByLocalDateTime(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * yyyy-MM-dd 转为date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateByYMDStr(String date) {
		return getDateByLocalDate(LocalDate.parse(date, FORMAT_YYYY_DD_MM));
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 转为date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateByYMDHMSStr(String date) {
		LocalDateTime date1 = LocalDateTime.parse(date, FORMAT_YYYY_DD_MM_HH_MM_SS);
		return getDateByLocalDateTime(date1);
	}

	/**
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getStrByLocalDateTime(LocalDateTime dateTime, String pattern) {
		return DateTimeFormatter.ofPattern(pattern).format(dateTime);
	}

	/**
	 * 
	 * 格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrByDate(Date date, String pattern) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return getStrByLocalDateTime(instant.atZone(zoneId).toLocalDateTime(), pattern);
	}

	/**
	 * yyyy-MM-dd 转为LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate getLocalDateByStr(String date) {
		return LocalDate.parse(date, FORMAT_YYYY_DD_MM);
	}

	/**
	 * yyyy-MM-dd 转为LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime getLocalDateTimeByStr(String date) {
		return LocalDateTime.parse(date, FORMAT_YYYY_DD_MM_HH_MM_SS);
	}

	/**
	 * 通过字符串获取时间戳
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static Long getLongByStr(String time, String pattern) {
		LocalDateTime date1 = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
		return date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
