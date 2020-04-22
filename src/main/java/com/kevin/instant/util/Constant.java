package com.kevin.instant.util;

/**
 * 常量
 * Created by Administrator on 2017/4/10.
 */
public class Constant {

	/**
	 * 验证码
	 */
	public static final String VERIFY_CODE = "verifyCode";

	/**
	 * 盐
	 */
	public static final String SALT = "instant@#kevin$%";

	/**
	 * 登录用户缓存key
	 */
	public final static String CACHE_KEY_PREFIX_LOGIN_USER = "INSTANT_USER_";
	/**
	 * 登录用户资源缓存key
	 */
	public final static String CACHE_KEY_PREFIX_RESOURCE = "INSTANT_RESOURCE_";
	/**
	 * 登录用户菜单缓存key
	 */
	public final static String CACHE_KEY_PREFIX_MENU = "INSTANT_MENU_";
	/**
	 * 微信小程序登录用户openid缓存key，value是session_key
	 */
	public final static String CACHE_KEY_PREFIX_OPENID = "INSTANT_OPENID_";
	/**
	 * 字典缓存key
	 */
	public final static String CACHE_KEY_PREFIX_DICT = "INSTANT_DICT_";
	/**
	 * 地区缓存
	 */
	public final static String CACHE_KEY_PREFIX_AREA = "INSTANT_AREA_";

	/**
	 * 上传图片最大数量9
	 */
	public static final int UPLOAD_PIC_MAX_NINE = 9;

	public static final String CURRENCY_YUAN = "元";

	/**
	 * 百分百，100%
	 */
	public static final String ONE_HUNDREND_PERCENT = "100%";
	/**
	 * 百分号
	 */
	public static final String PERCENT = "%";

	/**
	 * 登录cookie token
	 */
	public static final String TOKEN = "instantToken";
	/**
	 * cookie保存一周
	 */
	public static final int COOKIE_TIME_ONE_WEEK = 604800; //7 * 24 * 3600;

	/**
	 * 字符编码UTF-8
	 */
	public static final String CHARSET_UTF8 = "UTF-8";


	//*************************登录客户端类型************************
	/**
	 * 医生app端登录
	 */
	public static final String LOGIN_CLIENT_TYPE_APP = "1";
	/**
	 * web端登录
	 */
	public static final String LOGIN_CLIENT_TYPE_WEB = "2";
	/**
	 * 微信小程序端登录
	 */
	public static final String LOGIN_CLIENT_TYPE_WX_MINI = "3";
	/**
	 * 微信公众号端登录
	 */
	public static final String LOGIN_CLIENT_TYPE_WX_GZH = "4";

	/**
	 * Excel 2007 以后
	 */
	public static final String EXCEL_EXTEND_XLSX = "xlsx";

	/**
	 * Excel 2003 以后
	 */
	public static final String EXCEL_EXTEND_XLS = "xls";


}
