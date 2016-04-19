package com.xinwei.kanfangshenqi.util;

import com.xinwei.kanfangshenqi.app.XWApplication;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast toast;

	/**
	 * Toast 短提示，防止多次提示
	 */
	public static void show(Context context,String content) {
		if (toast != null) {
			toast.cancel();
		}
		if (content == null)
			return;
		try {
			toast = Toast.makeText(XWApplication.getInstance(), content, Toast.LENGTH_SHORT);
			toast.show();
		} catch (Exception e) {
		}
	}
	public static void show(String content){
		if (toast != null) {
			toast.cancel();
		}
		if (content == null)
			return;
		try {
			toast = Toast.makeText(XWApplication.getInstance(), content, Toast.LENGTH_SHORT);
			toast.show();
		} catch (Exception e) {
		}
	}
	/**
	 * Toast 长提示，防止多次提示
	 */
	public static void showLong(String content) {
		if (toast != null) {
			toast.cancel();
		}
		if (content == null)
			return;
		try {
			toast = Toast.makeText(XWApplication.getInstance(), content, Toast.LENGTH_LONG);
			toast.show();
		} catch (Exception e) {
		}
	}
}
