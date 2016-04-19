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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
public class ChangeSexActivity extends BaseActivity implements OnClickListener{
	
	@ViewInject(R.id.rv_man)
	private RelativeLayout rvMan;
	@ViewInject(R.id.rv_women)
	private RelativeLayout rvWomen;
	@ViewInject(R.id.man_select)
	private ImageView iv_man;
	@ViewInject(R.id.women_select)
	private ImageView iv_women;
	private PreferenceUtils preferenceUtils;
	private Boolean isMan =true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_change_sex);
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt("性别");
		setLeftTitle("个人信息");
		setRightTxt("保存");
//		String sex= getIntent().getStringExtra("sex");
		preferenceUtils = PreferenceUtils.getInstance(ChangeSexActivity.this);
		String sex=preferenceUtils.getSettingUserSex();
		if((Const.WOMAN).equals(sex)){
			iv_women.setVisibility(View.VISIBLE);
			isMan = false;
		}
		else{
			iv_man.setVisibility(View.VISIBLE);
			isMan = true;
		}
		rvMan.setOnClickListener(this);
		rvWomen.setOnClickListener(this);
		getRightTxt().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isMan){
					changeName(Const.WOMAN);
				}else{
					changeName(Const.MAN);
				}
			}
		});
	}
	@Override
	public void onReloadData() {
		
	}
	@Override
	public String getRequestTag() {
		return ChangeSexActivity.class.getSimpleName();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rv_man:
			iv_women.setVisibility(View.INVISIBLE);
			iv_man.setVisibility(View.VISIBLE);
			isMan = true;
			break;
		case R.id.rv_women:
			iv_women.setVisibility(View.VISIBLE);
			iv_man.setVisibility(View.INVISIBLE);
			isMan = false;
			break;
		}
		
	}
	private void changeName(final String sex){
		showBar();
		Map<String,String> paramsContent = new HashMap<String,String>();
		paramsContent.put(Const.PARAM_SEX, sex);
		HttpRequest.post(this, Const.URL_USERINFO_CHANGE, getRequestTag(), paramsContent,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				Toast.makeText(ChangeSexActivity.this, "性别修改成功", Toast.LENGTH_SHORT).show();
				
				preferenceUtils.setSettingUserSex(sex);
				if(WoDeFragment.woDeFragment!=null)
					WoDeFragment.woDeFragment.onChildViewLoaded();
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putString(Const.PARAM_SEX, sex);
				intent.putExtras(bundle);
				setResult(2,intent);
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
