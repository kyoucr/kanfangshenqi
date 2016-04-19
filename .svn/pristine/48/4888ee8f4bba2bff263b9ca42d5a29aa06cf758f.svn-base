package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.QRCodeUtils;
import com.xinwei.kanfangshenqi.util.Utils;

import android.os.Bundle;
import android.widget.ImageView;
/**
 ********************
 * 我的二维码
 * @author cn
 ********************
 */
public class MyQRActivity extends BaseActivity{
	@ViewInject(R.id.imgQR)
	private ImageView imgQR;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(null!=Utils.getHeaderParamsOnly()){
			addChildView(R.layout.activity_my_qr);
		}
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.txt_my_qr);
		setLeftTitle(R.string.user);
		imgQR.setImageBitmap(new QRCodeUtils(activity,150,150).createQR(PreferenceUtils.getInstance(activity).getSettingUserAccount()));
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return null;
	}

}
