package com.xinwei.kanfangshenqi.util;

import android.widget.TextView;
/**
 *************************
 * TextView写值工具
 * @author cn
 *************************
 */
public class TextViewWriterUtil {
	public static void writeValue(TextView txtView,String value){
		if(txtView==null)
			return;
		txtView.setText(ValidatorUtil.isValidString(value) ? value
				: "");
	}

}
