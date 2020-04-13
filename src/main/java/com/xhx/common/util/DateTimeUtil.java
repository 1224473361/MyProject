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
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDateTime转Date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getDateTimeDisplayString(LocalDateTime dateTime, String pattern) {
		return DateTimeFormatter.ofPattern(pattern).format(dateTime);
	}

	/**
	 * 格式 yyyy年MM月dd日 HH:mm:ss
	 *
	 * @param dateTime
	 * @return
	 */
	public static String getDateTimeDisplayString(LocalDateTime dateTime) {
		return getDateTimeDisplayString(dateTime, YYYY_MM_DD_HHMM_Z);
	}

	/**
	 * 格式yyyy-MM-dd HH:mm
	 *
	 * @param dateTime
	 * @return
	 */
	public static String getDateTimeDisplayString2(LocalDateTime dateTime) {
		return getDateTimeDisplayString(dateTime, YY_MM_DD_HHMMSS);
	}

	/**
	 * 格式yyyy-MM-dd HH:mm
	 *
	 * @param dateTime
	 * @return
	 */
	public static String getDateDisplayString(LocalDateTime dateTime) {
		return getDateTimeDisplayString(dateTime, YY_MM_DD);
	}

	/**
	 * 
	 * 格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateDisplayString(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return getDateDisplayString(instant.atZone(zoneId).toLocalDateTime());
	}

	/**
	 * yyyy-MM-dd 转为date
	 * 
	 * @param date
	 * @return
	 */
	public static Date asDate(String date) {
		return asDate(LocalDate.parse(date, FORMAT_YYYY_DD_MM));
	}
	
	/**
	 * yyyy-MM-dd 转为LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate asLocalDate(String date) {
		return LocalDate.parse(date, FORMAT_YYYY_DD_MM);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 转为date
	 * 
	 * @param date
	 * @return
	 */
	public static Date asDateYMDHMS(String date) {
		LocalDateTime date1 = LocalDateTime.parse(date, FORMAT_YYYY_DD_MM_HH_MM_SS);
		return asDate(date1);
	}

	/**
	 * yyyy-MM-dd 转为date
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime localDate(String date) {
		return LocalDateTime.parse(date, FORMAT_YYYY_DD_MM_HH_MM_SS);
	}
}
