package com.xinwei.kanfangshenqi.activity;

import java.util.HashMap;
import java.util.Map;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.ClearEditText;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ChangePWDActivity extends BaseActivity {
	@ViewInject(R.id.newPwdEdtTxt)
	private ClearEditText newPwdEdtTxt;
	@ViewInject(R.id.surePwdEdtTxt)
	private ClearEditText surePwdEdtTxt;
	@ViewInject(R.id.oldPwdEdtTxt)
	private ClearEditText oldPwdEdtTxt;
	@ViewInject(R.id.txtForgetPwd)
	private TextView txtForgetPwd;
	public static ChangePWDActivity INSTANCE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		INSTANCE = this;
		addChildView(R.layout.activity_change_pwd);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(getString(R.string.m_password));
		setLeftTitle("个人信息");
		setRightTxt(getString(R.string.save));
		getRightTxt().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changePwd();
			}
		});
	}

	private void changePwd() {
		String oldPwd = oldPwdEdtTxt.getText().toString().trim();
		if (!ValidatorUtil.isValidString(oldPwd)) {
			ToastUtil.show(activity, "请输入旧密码!");
			oldPwdEdtTxt.requestFocus();
			return;
		}
		final String newPwd = newPwdEdtTxt.getText().toString().trim();
		if (!ValidatorUtil.isValidString(newPwd)) {
			ToastUtil.show(activity, "请输入新密码!");
			newPwdEdtTxt.requestFocus();
			return;
		}
		String surePwd = surePwdEdtTxt.getText().toString().trim();
		if (!ValidatorUtil.isValidString(surePwd)) {
			ToastUtil.show(activity, "请再次输入密码!");
			surePwdEdtTxt.requestFocus();
			return;
		}
		if(!newPwd.equals(surePwd)){
			ToastUtil.show(activity, "新密码和再次输入密码不一致!");
			return;
		}
		showBar();
		Map<String, String> paramsContent = new HashMap<String, String>();
		paramsContent.put(Const.PARAM_PWD, Utils.getMD5(oldPwd));
		HttpRequest.get(activity, Const.URL_PWD_VALIDATE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {
					@Override
					public void onSuccess(String url, String responseResult) {
						//校验密码成功后，修改密码
						Map<String, String> paramsContent = new HashMap<String, String>();
						paramsContent.put(Const.PARAM_PWD, Utils.getMD5(newPwd));
						HttpRequest.post(activity, Const.URL_USERINFO_CHANGE,
								getRequestTag(), paramsContent,
								Utils.getHeaderParamsOnly(),
								new RequestListener() {
									@Override
									public void onSuccess(String url,
											String responseResult) {
										PreferenceUtils.getInstance(activity).setSettingUserPassWord(newPwd);
										ToastUtil.show(activity, "密码修改成功！");
										finish();
									}

									@Override
									public void onFailure(String url,
											String errorInfo) {
										closeBar();
									}

									@Override
									public void onError(String url,
											String responseResult) {
										closeBar();
									}
								});
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						closeBar();
					}
				},true);

	}
	@Event(R.id.txtForgetPwd)
	private void toForgetPwdEvent(View v){
		Bundle data = new Bundle();
		data.putString(FindPwdActivity.class.getSimpleName(), getString(R.string.m_password));
		Utils.moveTo(activity, FindPwdActivity.class, false, data);
	}
	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return ChangePWDActivity.class.getSimpleName();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		INSTANCE = null;
	}

}