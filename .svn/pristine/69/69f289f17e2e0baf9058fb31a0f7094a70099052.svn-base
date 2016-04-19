package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.NotifyList.Notify;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 ********************
 * 通知详情
 * @author cn
 ********************
 */
public class NotifyDetailInfoActivity extends BaseActivity{
	@ViewInject(R.id.txtTitleSub)
	private TextView txtTitleSub;
	@ViewInject(R.id.txtTime)
	private TextView txtTime;
	@ViewInject(R.id.txtContent)
	private TextView txtContent;
	private String pushId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pushId = getIntent().getStringExtra(NotifyDetailInfoActivity.class.getSimpleName());
		if(pushId==null)
			finish();
		else{
			addChildView(R.layout.activity_notify_detail_info);
		}
		
	}
	@Override
	public void onChildViewLoaded() {
		setLeftTitle(R.string.title_notify);
		isShowContent(false);
		onReloadData();
	}

	@Override
	public void onReloadData() {
		showBar();
		HttpRequest.get(activity, Const.URL_NOTIFY_DETAIL_INFO+pushId, getRequestTag(), null,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				Notify notify = new Gson().fromJson(responseResult, Notify.class);
				if(ValidatorUtil.isValidString(notify.getPushTime()))
					TextViewWriterUtil.writeValue(txtTime, notify.getPushTime());
				else
					txtTime.setVisibility(View.GONE);
				TextViewWriterUtil.writeValue(txtTitleSub, notify.getTitle());
				TextViewWriterUtil.writeValue(txtContent, notify.getContent());
				isShowContent(true);
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				isShowError(true);
			}
			
			@Override
			public void onError(String url, String responseResult) {
				isShowError(true);
			}
		},true);
	}

	@Override
	public String getRequestTag() {
		return NotifyDetailInfoActivity.class.getSimpleName();
	}

}
