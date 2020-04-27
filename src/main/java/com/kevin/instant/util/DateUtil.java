package com.kevin.instant.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	public static final String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String FORMAT_FULL_NO_SECONDS_CN = "yyyy年MM月dd日 HH时mm分";
	public static final String FORMAT_YEAR_MONTH_DATE_CN = "yyyy年MM月dd日";

	/**
	 * 日期格式：日期明码格式(yyyy-MM-dd HH:mm:ss)
	 */
	public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式:本地日期明码格式(yyyy-MM-dd HH:mm)
	 */
	public static final String FORMAT_FULL_NO_SECONDS = "yyyy-MM-dd HH:mm";

	/**
	 * 日期格式：年月日(yyyy-MM-dd)
	 */
	public static final String FORMAT_YEAR_MONTH_DATE = "yyyy-MM-dd";
	/**
	 * 日期格式：年月日(yyyyMMdd)
	 */
	public static final String FORMAT_YEAR_MONTH_DATE_NO_MODIFIER = "yyyyMMdd";

	/**
	 * 日期格式：时分秒(HH:mm:ss)
	 */
	public static final String FORMAT_TIME = "HH:mm:ss";
	/**
	 * 日期格式：时分秒(HHmmss)
	 */
	public static final String FORMAT_TIME_NO_MODIFIER = "HHmmss";

	/**
	 * 时间转字符窜
	 * @param date
	 * @param format
	 * @return String 返回类型
	 */
	public static String date2Str(Date date, String format) {
		if( date==null || format==null || format.trim().length()==0 ) {
			throw new RuntimeException("DateUtil.string2Date: parameters[date, format] can not be null/empty");
		}

		DateFormat df = getSimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 字符窜转时间
	 * @param param
	 * @param format
	 * @return Date 返回类型
	 * @throws ParseException
	 */
	public static Date str2Date(String param, String format) {
		if( param==null || param.trim().length()==0 || format==null || format.trim().length()==0 ) {
			throw new RuntimeException("DateUtil.string2Date: parameters[param, format] can not be null");
		}

		DateFormat df = getSimpleDateFormat(format);
		try {
			return df.parse(param);
		} catch (ParseException e) {
			throw new RuntimeException("DateUtil.string2Date: 转换失败", e);
		}
	}

	/**
	 * yyyy-MM-dd HH:mm:ss格式字符串转换为Date
	 * @param dateStr
	 * @return
	 */
	public static final Date defaultStr2Date(String dateStr){
		return str2Date(dateStr, FORMAT_FULL);
	}

	/**
	 * Date转为yyyy-MM-dd HH:mm:ss字符串
	 * @param date
	 * @return
	 */
	public static String defaultDate2Str(Date date){
		return date2Str(date, FORMAT_FULL);
	}

	/**
	 * 时间转换：java.time.LocalDateTime --> java.util.Date
	 * @param localDateTime
	 * @return localDateTime为null，返回null
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		if (localDateTime==null) {
			throw new RuntimeException("DateUtil.toDate: parameters[localDateTime] can not be null");
		}
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * 时间转换：java.time.LocalDate --> java.util.Date
	 * @param localDate
	 * @return localDate为null，返回null
	 */
	public static Date toDate(LocalDate localDate) {
		if (localDate==null) {
			throw new RuntimeException("DateUtil.toDate: parameters[localDate] can not be null");
		}
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * 时间转换
	 * @param localDate
	 * @param localTime
	 * @return localDate或localTime为null，返回null
	 */
	public static Date toDate(LocalDate localDate, LocalTime localTime) {
		if (localDate==null || localTime==null) {
			throw new RuntimeException("DateUtil.toDate: parameters[localDate, localTime] can not be null");
		}
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * 时间转换：java.util.Date --> java.time.LocalDateTime
	 * @param date
	 * @return date为null，返回null
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		if (date==null) {
			throw new RuntimeException("DateUtil.toLocalDateTime: parameters[date] can not be null");
		}
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	/**
	 * 时间转换：java.util.Date --> java.time.LocalDate
	 * @param date
	 * @return date为null，返回null
	 */
	public static LocalDate toLocalDate(Date date){
		if (date==null) {
			throw new RuntimeException("DateUtil.toLocalDate: parameters[date] can not be null");
		}
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate;
	}

	/**
	 * 时间转换：java.util.Date --> java.time.LocalTime
	 * @param date
	 * @return date为null，返回null
	 */
	public static LocalTime toLocalTime(Date date) {
		if (date==null) {
			throw new RuntimeException("DateUtil.toLocalTime: parameters[date] can not be null");
		}
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalTime localTime = localDateTime.toLocalTime();
		return localTime;
	}

	//==================================================================================================

	/**
	 * 给date添加天数
	 * @param date 指定日期
	 * @param days 长度，当为负数时，等于减去天数
	 */
	public static Date addDays(Date date, int days){
		if (date==null) {
			throw new RuntimeException("DateUtil.addDays: parameters[date] can not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * 给date添加分钟数
	 * @param date 指定日期
	 * @param minutes 长度，当为负数时，等于减去分钟数
	 */
	public static Date addMinutes(Date date, int minutes){
		if (date==null) {
			throw new RuntimeException("DateUtil.addDays: parameters[date] can not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * date所在年份的第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfYear(Date date){
		LocalDate localDate = toLocalDate(date);
		LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.firstDayOfYear());
		return toDate(lastDayOfThisMonth);
	}

	/**
	 * date所在年份的最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfYear(Date date){
		LocalDate localDate = toLocalDate(date);
		LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfYear());
		return toDate(lastDayOfThisMonth);
	}

	/**
	 * date所在月份的第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date){
		LocalDate localDate = toLocalDate(date);
		LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
		return toDate(lastDayOfThisMonth);
	}

	/**
	 * date所在月份的最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date){
		LocalDate localDate = toLocalDate(date);
		LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
		return toDate(lastDayOfThisMonth);
	}

	/**
	 * 计算两个日期的间隔天数
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public static int daysInterval(Date startDate, Date endDate) {
		return daysInterval(toLocalDate(startDate), toLocalDate(endDate));
	}

	/**
	 * jdk8的：计算两个日期的间隔天数
	 * @param startDate 开始，不能为null
	 * @param endDate 结束，不能为null
	 * @return 如果endDate在startDate之前，那么返回的会是负数
	 */
	public static int daysInterval(LocalDate startDate, LocalDate endDate){
		if (startDate==null || endDate==null) {
			throw new RuntimeException("DateUtil.daysInterval: parameters[startDate, endDate] can not be null");
		}
		int interval = Long.valueOf(startDate.until(endDate, ChronoUnit.DAYS)).intValue();
		return interval;
	}

	/**
	 * 计算两个时间的间隔小时数
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public static int hoursInterval(Date startDate, Date endDate) {
		return hoursInterval(toLocalDateTime(startDate), toLocalDateTime(endDate));
	}

	/**
	 * jdk8的：计算两个时间的间隔小时数
	 * @param startDatetime 开始，不能为null
	 * @param endDatetime 结束，不能为null
	 * @return 如果endDate在startDate之前，那么返回的会是负数
	 */
	public static int hoursInterval(LocalDateTime startDatetime, LocalDateTime endDatetime){
		if (startDatetime==null || endDatetime==null) {
			throw new RuntimeException("DateUtil.hoursInterval: parameters[startDatetime, endDatetime] can not be null");
		}
		int interval = Long.valueOf(startDatetime.until(endDatetime, ChronoUnit.HOURS)).intValue();
		return interval;
	}

	/**
	 * 计算两个时间的间隔分钟数
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public static int minutesInterval(Date startDate, Date endDate) {
		return minutesInterval(toLocalDateTime(startDate), toLocalDateTime(endDate));
	}

	/**
	 * jdk8的：计算两个时间的间隔分钟数
	 * @param startDatetime 开始，不能为null
	 * @param endDatetime 结束，不能为null
	 * @return 如果endDate在startDate之前，那么返回的会是负数
	 */
	public static int minutesInterval(LocalDateTime startDatetime, LocalDateTime endDatetime){
		if (startDatetime==null || endDatetime==null) {
			throw new RuntimeException("DateUtil.minutesInterval: parameters[startDatetime, endDatetime] can not be null");
		}
		int interval = Long.valueOf(startDatetime.until(endDatetime, ChronoUnit.MINUTES)).intValue();
		return interval;
	}

	/**
	 * 一天的开始，例如2018-12-12 00:00:00
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date startOfDay(String date, String format) {
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
		return startOfDay(localDate);
	}

	/**
	 * 一天的开始，例如2018-12-12 00:00:00
	 * @param date
	 * @return
	 */
	public static Date startOfDay(TemporalAccessor date) {
		LocalDate localDate = LocalDate.from(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 一天的开始，例如2018-12-12 00:00:00
	 * @param date
	 * @return
	 */
	public static Date startOfDay(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 一天的开始，例如2018-12-12 00:00:00
	 * @param date
	 * @return
	 */
	public static Date startOfDay(Date date) {
		LocalDate localDate = toLocalDate(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 一天的结束，例如2018-12-12 23:59:59
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date endOfDay(String date, String format){
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
		return endOfDay(localDate);
	}

	/**
	 * 一天的结束，例如2018-12-12 23:59:59
	 * @param date
	 * @return
	 */
	public static Date endOfDay(TemporalAccessor date) {
		LocalDate localDate = LocalDate.from(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	/**
	 * 一天的结束，例如2018-12-12 23:59:59
	 * @param date
	 * @return
	 */
	public static Date endOfDay(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	/**
	 * 一天的结束，例如2018-12-12 23:59:59
	 * @param date
	 * @return
	 */
	public static Date endOfDay(Date date) {
		LocalDate localDate = toLocalDate(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	/**
	 * 获取yyyyMMddHHmmss格式
	 * 
	 * @return
	 */
	public static String getTimeNotModifier() {
		SimpleDateFormat sdfTimeNotModifier = getSimpleDateFormat(yyyyMMddHHmmss);
		return sdfTimeNotModifier.format(new Date());
	}

	/**
	 * 返回精确到毫秒的日期字符串，格式为 yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String getTimestamp(){
		SimpleDateFormat fmt = getSimpleDateFormat(yyyyMMddHHmmssSSS); //精确到毫秒
		return fmt.format(new Date());
	}

	/**
	 * 返回精确到秒的日期字符串，时间格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDefaultDateTime() {
		return defaultDate2Str(new Date());
	}

	/**
	 * 日期路径 即年/月/日 如2018/08/08
	 */
	public static String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 日期路径 即年/月/日 如20180808
	 */
	public static String dateTime() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyyMMdd");
	}

	private static SimpleDateFormat getSimpleDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	public static void main(String[] args) {

		Date lastDayOfMonth = lastDayOfMonth(new Date());
		System.out.println(lastDayOfMonth);

		System.out.println(startOfDay(lastDayOfMonth));
		System.out.println(endOfDay(lastDayOfMonth));

		Date now = new Date();
		System.out.println("now = "+ now);
		System.out.println("减去2天：" + addDays(now, -2));
		System.out.println("添加0天：" + addDays(now, 0));
		System.out.println("添加3天：" + addDays(now, 3));

		LocalDateTime localDateTime1 = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime1.plus(80, ChronoUnit.MINUTES);
		System.out.println(DateUtil.minutesInterval(localDateTime1, localDateTime2));

		Date startTime = DateUtil.str2Date("8:00:00", DateUtil.FORMAT_TIME);
		Date endTime = DateUtil.str2Date("12:01:00", DateUtil.FORMAT_TIME);
		int i = DateUtil.minutesInterval(startTime, endTime);
		System.out.println("分钟间隔数："+ i + ", 有" + (i / 8) + "个8分钟");

		System.out.println(addMinutes(now, 8));

		System.out.println(addMinutes(defaultStr2Date("2020-04-19 23:58:00"), 8));
	}
}
