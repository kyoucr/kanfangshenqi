package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.ViewInject;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.request.EditUserInfoRequest;
import com.xinwei.kanfangshenqi.response.EditUserInfoResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.WebUtils;
import com.xinwei.kanfangshenqi.view.ClearEditText;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SetPassWordActivity extends BaseActivity implements
		Response.ErrorListener, OnClickListener {

	@ViewInject(R.id.password)
	private ClearEditText mPassWord;
	@ViewInject(R.id.password_agen)
	private ClearEditText mPassWordAgen;
	private PreferenceUtils preferenceUtils;
	private String phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_set_password);
	}

	@Override
	public void onChildViewLoaded() {
		preferenceUtils = PreferenceUtils
				.getInstance(SetPassWordActivity.this);
		if (getIntent().getBooleanExtra("islogin", false)) {
			phone = getIntent().getStringExtra("phone");
			setTitleTxt("设置密码");
			setLeftTitle("修改密码");
			setRightTxt("保存");
			getRightTxt().setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkInput()) {
						changePasswordFromIsLOgin(Utils.getMD5(mPassWordAgen
								.getText().toString()), phone);
					}
				}
			});
		} else {
			setTitleTxt("设置密码");
			setLeftTitle("个人信息");
			setRightTxt("保存");
			// String password= getIntent().getStringExtra("password");
			getRightTxt().setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (checkInput()) {
						changePassword(Utils.getMD5(mPassWordAgen.getText()
								.toString()));
					} else {
					}
				}
			});
		}
	}

	@Override
	public void onReloadData() {

	}

	@Override
	public String getRequestTag() {
		return null;
	}

	@Override
	public void onClick(View v) {
	}

	private boolean checkInput() {
		if (StringUtils.isEmpty(mPassWord.getText().toString())) {
			Utils.makeToastAndShow(this, "密码不能为空,请重新输入", Toast.LENGTH_SHORT);
			return false;
		}
		if (StringUtils.isEmpty(mPassWordAgen.getText().toString())) {
			Utils.makeToastAndShow(this, "确认密码不能为空，请重新输入", Toast.LENGTH_SHORT);
			return false;
		}
		if (!mPassWord.getText().toString()
				.equals(mPassWordAgen.getText().toString())) {
			Utils.makeToastAndShow(this, "两次输入密码不一致", Toast.LENGTH_SHORT);
			return false;
		}
		return true;
	}

	private void changePassword(String password) {
		EditUserInfoRequest request = new EditUserInfoRequest(
				new Listener<EditUserInfoResponse>() {

					@Override
					public void onResponse(EditUserInfoResponse response) {
						closeBar();
						if (response.getStatus().equals("1")) {
							preferenceUtils.setSettingUserPassWord(mPassWordAgen
									.getText().toString());
							if(FindPwdActivity.INSTANCE!=null)
								FindPwdActivity.INSTANCE.finish();
							Toast.makeText(SetPassWordActivity.this,
									"密码设置成功！", Toast.LENGTH_SHORT).show();
						}else if (response != null && response.getStatus().equals("2")) {
							Utils.makeToastAndShow(SetPassWordActivity.this, "用户登录失效", Toast.LENGTH_SHORT);
							Bundle data = new Bundle();
							data.putBoolean(Const.FLAG_UNLOGIN, true);
							Utils.moveTo(activity, LoginActivity.class, false, data);
						}
						
						else{
							ToastUtil.show(activity, response.getMsg());
						}
					}
				}, this);
		request.setPassword(password);
		request.setPhone("");
		request.setNickName("");
		request.setSex("");
		request.setCompanyAddr("");
		request.setCompanyLatitude("");
		request.setCompanyLongitude("");
		request.setHomeAddr("");
		request.setHomeLatitude("");
		request.setHomeLongitude("");
		request.setToken(PreferenceUtils.getInstance(activity)
				.getSettingUserToken());
		request.setAppagent(PreferenceUtils.getInstance(activity)
				.getSettingUserAppAgent());
		request.setOsver(PreferenceUtils.getInstance(activity)
				.getSettingUserOSVer());
		request.setTransid(PreferenceUtils.getInstance(activity)
				.getSettingUserTransId());
		WebUtils.doPost(request);
		showBar();
	}

	private void changePasswordFromIsLOgin(String password, String phone) {
		showBar();
		EditUserInfoRequest request = new EditUserInfoRequest(
				new Listener<EditUserInfoResponse>() {

					@Override
					public void onResponse(EditUserInfoResponse response) {
						if (response.getStatus().equals("1")) {
							if(FindPwdActivity.INSTANCE!=null)
								FindPwdActivity.INSTANCE.finish();
							Toast.makeText(SetPassWordActivity.this,
									"密码设置成功！", Toast.LENGTH_SHORT).show();
							preferenceUtils.setSettingUserPassWord(Utils.getMD5(mPassWordAgen
									.getText().toString()));
//							Utils.moveTo(activity, LoginActivity.class);
							finish();
						}else if (response != null && response.getStatus().equals("2")) {
							Utils.makeToastAndShow(SetPassWordActivity.this, "用户登录失效", Toast.LENGTH_SHORT);
							Bundle data = new Bundle();
							data.putBoolean(Const.FLAG_UNLOGIN, true);
							Utils.moveTo(activity, LoginActivity.class, false, data);
						}
						
						else{
							closeBar();
							ToastUtil.show(activity, response.getMsg());
						}
					}
				}, this);
		request.setPhone(phone);
		request.setPassword(password);
		request.setNickName("");
		request.setSex("");
		request.setCompanyAddr("");
		request.setCompanyLatitude("");
		request.setCompanyLongitude("");
		request.setHomeAddr("");
		request.setHomeLatitude("");
		request.setHomeLongitude("");
		request.setToken(PreferenceUtils.getInstance(activity)
				.getSettingUserToken());
		request.setAppagent(PreferenceUtils.getInstance(activity)
				.getSettingUserAppAgent());
		request.setOsver(PreferenceUtils.getInstance(activity)
				.getSettingUserOSVer());
		request.setTransid(PreferenceUtils.getInstance(activity)
				.getSettingUserTransId());
		WebUtils.doPost(request);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		closeBar();
		ToastUtil.show(activity, getString(R.string.hint_error_net));
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
