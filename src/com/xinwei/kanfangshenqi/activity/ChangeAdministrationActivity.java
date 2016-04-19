package com.xinwei.kanfangshenqi.activity;

import java.util.HashMap;
import java.util.Map;

import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.response.AccountForAliPayResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.ClearEditText;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
public class ChangeAdministrationActivity extends BaseActivity{
	
	@ViewInject(R.id.alipay_input)
	private ClearEditText alipay_input;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_change_administration);
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt("支付宝提现");
		setLeftTitle("个人信息");
		setRightTxt("确定");
		if(!"".equals(PreferenceUtils.getInstance(activity).getSettingUserAlipayAcountName()))
			alipay_input.setText(PreferenceUtils.getInstance(activity).getSettingUserAlipayAcountName());	
		getRightTxt().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkInput()) {
					changeAliPay(alipay_input.getText().toString());
				}
			}
		});
	}
	@Override
	public void onReloadData() {
		
	}
	@Override
	public String getRequestTag() {
		return ChangeAdministrationActivity.class.getSimpleName();
	}
	private boolean checkInput() {
		if (StringUtils.isEmpty(alipay_input.getText().toString())) {
			Utils.makeToastAndShow(this, "支付宝账号不能为空,请重新输入", Toast.LENGTH_SHORT);
			return false;
		}
		return true;
	}
	private void changeAliPay(String accountNum){
		showBar();
		Map<String,String> paramsContent = new HashMap<String,String>();
		paramsContent.put(Const.PARAM_ACCOUNTID, PreferenceUtils.getInstance(activity).getSettingUserAlipayAcountId());
		paramsContent.put(Const.PARAM_ACCOUNTNUM, accountNum);
		paramsContent.put(Const.PARAM_ACCOUNTTYPE, "0");
		paramsContent.put(Const.PARAM_ISDEFAULT, "1");
		HttpRequest.post(this, Const.URL_USERINFO_CHANGE, getRequestTag(), paramsContent,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				AccountForAliPayResponse accountForAliPayResponse = new Gson().fromJson(responseResult, AccountForAliPayResponse.class);
				Toast.makeText(ChangeAdministrationActivity.this, "支付宝账号修改成功", Toast.LENGTH_SHORT).show();
				PreferenceUtils.getInstance(activity).setSettingUserAlipayAcountId(accountForAliPayResponse.getAccountId()+"");
				PreferenceUtils.getInstance(activity).setSettingUserAlipayAcountName(accountForAliPayResponse.getAccountNum());
				finish();
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				closeBar();
			}
			
			@Override
			public void onError(String url, String responseResult) {
				closeBar();
			}
		});
	}
}
