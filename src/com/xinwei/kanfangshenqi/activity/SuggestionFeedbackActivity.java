package com.xinwei.kanfangshenqi.activity;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.request.SuggestionFeedbackRequest;
import com.xinwei.kanfangshenqi.response.SuggestionFeedbackResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.WebUtils;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SuggestionFeedbackActivity extends BaseActivity implements
		TextWatcher{
	private EditText et_feedback;
	private TextView tv_text;
	// 输入 的最大字数
	private int maxsum = 140;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_suggestionfeedback);
	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		tv_text.setText("还可输入" + (maxsum - arg0.length()) + "字");

	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.suggestionfeedback_title_name);
		setLeftTitle(R.string.tab_bar_ic_wode);
		setRightTxt(R.string.submit);
		et_feedback = (EditText) findViewById(R.id.suggestionfeedback_help_feedback);
		tv_text = (TextView) findViewById(R.id.suggestionfeedback_tv_text);
		et_feedback.addTextChangedListener(this);
		getRightTxt().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!et_feedback.getText().toString().equals("")) {
					setQingDan(et_feedback.getText().toString());
				}else{
					Toast.makeText(SuggestionFeedbackActivity.this, "请输入反馈内容", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onReloadData() {
		
	}

	@Override
	public String getRequestTag() {
		return SuggestionFeedbackActivity.class.getSimpleName();
	}
	private void setQingDan(String contentValue){
		showBar();
		Map<String,String> paramsContent = new HashMap<String,String>();
		paramsContent.put(Const.PARAM_CONTENTVALUE, contentValue);
		HttpRequest.post(this, Const.URL_FEEDBACK, getRequestTag(), paramsContent,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				ToastUtil.show("非常感谢您的反馈！");
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
		},false);
	}
}