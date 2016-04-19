package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.TradeDetail;
import com.xinwei.kanfangshenqi.model.TradeRecordList.TradeRecord;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 ********************
 * 交易详情
 * @author cn
 ********************
 */
public class TradeDetailInfoActivity extends BaseActivity {
	@ViewInject(R.id.txtStatus)
	private TextView txtStatus;
	@ViewInject(R.id.btnResubmit)
	private Button btnResubmit;
	@ViewInject(R.id.txtMoney)
	private TextView txtMoney;
	@ViewInject(R.id.txtType)
	private TextView txtType;
	@ViewInject(R.id.txtBillNO)
	private TextView txtBillNO;
	@ViewInject(R.id.txtTime)
	private TextView txtTime;
	private String applicationId;
	private TradeDetail tradeDetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_trade_detail_info);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.title_trade_detail);
		setLeftTitle(R.string.trade_record);
		TradeRecord tradeRecord = (TradeRecord) getIntent().getSerializableExtra(TradeRecord.class.getSimpleName());
		if(tradeRecord==null){
			finish();
			return;
		}else{
			applicationId = tradeRecord.getApplicationId();
			if(applicationId==null){
				finish();
				return;
			}
		}
		onReloadData();
	}

	@Override
	public void onReloadData() {
		showBar();
		HttpRequest.get(activity, Const.URL_TRADE_DETAIL+applicationId, getRequestTag(),null, Utils.getHeaderParamsOnly(),new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				isShowContent(true);
				tradeDetail = new Gson().fromJson(responseResult, TradeDetail.class);
				if(tradeDetail==null)
					return;
				initView(tradeDetail);
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
	private void initView(TradeDetail tradeDetail){
		TextViewWriterUtil.writeValue(txtStatus, tradeDetail.getCashStatus());
		TextViewWriterUtil.writeValue(txtMoney, tradeDetail.getPrice());
		TextViewWriterUtil.writeValue(txtType, tradeDetail.getCashType());
		TextViewWriterUtil.writeValue(txtStatus, tradeDetail.getCashStatus());
		if(tradeDetail.isFailed()){
			txtStatus.setTextColor(getResources().getColor(R.color.orange));
			btnResubmit.setVisibility(View.VISIBLE);
		}
		TextViewWriterUtil.writeValue(txtBillNO, tradeDetail.getApplicationId());
		TextViewWriterUtil.writeValue(txtTime, tradeDetail.getApplicationTime());
	}
	@Event(R.id.btnResubmit)
	private void reSubmitEvent(View v){
		btnResubmit.setEnabled(false);
		showBar();
		setBarCancelable(false);
		HttpRequest.get(activity, Const.URL_RESUBMIT_CASH+applicationId, getRequestTag(), null,Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				finish();
				ToastUtil.show(activity, getString(R.string.hint_submit_cash_success));
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				closeBar();
				btnResubmit.setEnabled(true);
			}
			
			@Override
			public void onError(String url, String responseResult) {
				closeBar();
				btnResubmit.setEnabled(true);
			}
		},true);
	}
	@Override
	public String getRequestTag() {
		return TradeDetailInfoActivity.class.getSimpleName();
	}

}
