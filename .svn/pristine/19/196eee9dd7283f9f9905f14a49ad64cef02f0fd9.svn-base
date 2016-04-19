package com.xinwei.kanfangshenqi.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
	/**
	 ************************* 
	 * 判断字符串是否有效 （null或者""则视为无效）
	 * 
	 * @param str
	 * @return
	 ************************* 
	 */
	public static boolean isValidString(String str) {
		if (str == null || "".equals(str))
			return false;
		return true;
	}
	/**
	 ********************
	 * 是不是有效的int
	 * @param str
	 * @return
	 ********************
	 */
	public static boolean isValidInt(String str){
		 try {
			int intStr = Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 ************************* 
	 * 查看是否是有效集合
	 * 
	 * @param list
	 * @return
	 ************************* 
	 */
	public static boolean isValidList(List list) {
		if (list == null || list.size() == 0)
			return false;
		return true;
	}

	/*******************
	 * 验证手机号
	 * 
	 * @param mobiles
	 * @return
	 *******************/
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern
				.compile("^((14[0-9])|(17[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/*******************
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 *******************/
	public static boolean isEmail(String email) {

		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher m = pattern.matcher(email);
		return m.matches();

	}

	/*******************
	 * 验证身份证
	 * 
	 * @param idCard
	 * @return
	 *******************/
	public static boolean isIdCard(String idCard) {
		Pattern p1 = Pattern
				.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X)$");// 18位
		Pattern p2 = Pattern
				.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");// 15位
		Matcher m1 = p1.matcher(idCard);
		Matcher m2 = p2.matcher(idCard);
		return m1.matches() || m2.matches();
	}

	/**
	 ************************* 
	 * 验证IPV4地址
	 * 
	 * @param ip
	 * @return
	 ************************* 
	 */
	public static boolean isIpv4(String ipAddress) {
		String ipPattern = "^((([1-9]\\d?)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}(([1-9]\\d?)|(1\\d{2})|(2[0-4]\\d)|(25[0-5])|[0])$";
		Pattern pattern = Pattern.compile(ipPattern);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
}
