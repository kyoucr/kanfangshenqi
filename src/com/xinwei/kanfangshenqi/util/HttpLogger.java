package com.xinwei.kanfangshenqi.util;

import android.util.Log;

/**
 * 
  * @ClassName: Logger
  * @Description: 日志工具类
  * @author donghong
  * @date 2011-9-14 上午10:57:57
  *
 */
public class HttpLogger {

//	public static void logRestApiException(String tag, HttpApiException exception) {
//		String log = String
//				.format("host:[%s], apipath:[%s], params:[%s], exception:[%s]", exception.getHostUrl(), exception
//						.getApiPath(), exception.getParamValues(), exception.getException());
//		Log.d(tag, log);
//	}

	public static void logHttpException(String url, String excetpion) {
		String host = getHostFromUrl(url);
		String apiPath = getApiPath(url);
		String paramsValue=getParamsFromUrl(url);
		String log = String
				.format("host:[%s], apipath:[%s], params:[%s], exception:[%s]", host, apiPath, paramsValue, excetpion);
		Log.d("HTTP", log);
	}

	public static void logHttpException(String url, String content, String excetpion) {
		String host = getHostFromUrl(url);
		String apiPath = getApiPath(url);
		String log = String
				.format("host:[%s], apipath:[%s], params:[%s], exception:[%s]", host, apiPath, content, excetpion);
		Log.d("HTTP", log);
	}

	private static String getParamsFromUrl(String url) {
		int startIndex = url.indexOf('?') + 1;
		String paramValues = url.substring(startIndex, url.length());
		return paramValues;
	}

	private static String getHostFromUrl(String url) {
		int protrolStart = url.indexOf("//");
		int hostEnd = url.indexOf('/', protrolStart + 2);
		String host = url.substring(0, hostEnd);
		return host;
	}

	private static String getApiPath(String url) {
		int protrolStart = url.indexOf("//");
		int hostEnd = url.indexOf('/', protrolStart + 2);
		int apiStart = url.indexOf('?');
		if (apiStart == -1)
			apiStart = url.length();
		String apiPath = url.substring(hostEnd, apiStart);
		return apiPath;
	}
}
