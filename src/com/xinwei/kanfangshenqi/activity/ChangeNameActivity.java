package com.xinwei.kanfangshenqi.activity;

import java.util.HashMap;
import java.util.Map;

import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.WoDeFragment;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.ClearEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
public class ChangeNameActivity extends BaseActivity{
	
	/**
	 * 自定义TitleBar
	 * 
	 */
	@ViewInject(R.id.et_name)
	private ClearEditText mEditText;
	private PreferenceUtils preferenceUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_change_name);
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt("名字");
		setLeftTitle("取消");
		setRightTxt("保存");
//		String name= getIntent().getStringExtra("name");
//		mEditText.setText(name);
		preferenceUtils = PreferenceUtils.getInstance(ChangeNameActivity.this);
		mEditText.setText(preferenceUtils.getSettingUserNickName());
		getRightTxt().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!mEditText.getText().toString().equals("")) {
					changeName(mEditText.getText().toString());
				}else{
					Toast.makeText(ChangeNameActivity.this, "请输入要修改的昵称", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	@Override
	public void onReloadData() {
		
	}
	@Override
	public String getRequestTag() {
		return ChangeNameActivity.class.getSimpleName();
	}
	private void changeName(String nickName){
		showBar();
		Map<String,String> paramsContent = new HashMap<String,String>();
		paramsContent.put(Const.PARAM_NICKNAME, nickName);
		HttpRequest.post(this, Const.URL_USERINFO_CHANGE, getRequestTag(), paramsContent,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				Toast.makeText(ChangeNameActivity.this, "昵称修改成功", Toast.LENGTH_SHORT).show();
				preferenceUtils.setSettingUserNickName(mEditText.getText().toString());
				if(WoDeFragment.woDeFragment!=null)
					WoDeFragment.woDeFragment.onChildViewLoaded();
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putString("name", mEditText.getText().toString());
				intent.putExtras(bundle);
				setResult(1,intent);
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
