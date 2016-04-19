package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;

import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.LouPanQingDanListItemAdapter;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.JiHuaFragment;
import com.xinwei.kanfangshenqi.fragment.ZhaoFangFragment;
import com.xinwei.kanfangshenqi.model.PlansInfo;
import com.xinwei.kanfangshenqi.model.PlansModel;
import com.xinwei.kanfangshenqi.model.RemovePlan;
import com.xinwei.kanfangshenqi.model.UnPlan;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.request.PlansRequest;
import com.xinwei.kanfangshenqi.response.NameOfHousesResponse;
import com.xinwei.kanfangshenqi.response.PlansResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.WebUtils;
import com.xinwei.kanfangshenqi.view.AlertWidget;
import com.xinwei.kanfangshenqi.view.SlideListView;
import com.xinwei.kanfangshenqi.view.SlideView.OnSlideListener;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LouPanActivity extends BaseActivity{
	private ArrayList<PlansModel> plansModel;
	private ArrayList<PlansInfo> plansInfo = new ArrayList<PlansInfo>();
	private LayoutInflater inflater;
	private SlideListView listView;
	private LouPanQingDanListItemAdapter adapter;
	private PopupWindow popuWindow1;
	private View contentView1;
	private PopupWindow popuWindow;
	private View contentView;
	private PreferenceUtils preferenceUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_loupan);
	}

	private void getLouPan() {
		showBar();
		HttpRequest.get(activity, Const.URL_PLANS, getRequestTag(), null,Utils.getHeaderParamsOnly(),new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				PlansResponse plansResponse = new Gson().fromJson(responseResult, PlansResponse.class);
				plansModel = plansResponse.getDataList();
				if(ValidatorUtil.isValidList(plansModel)){
					for(int i=0;i<plansModel.size();i++){
						for(int j=0;j<plansModel.get(i).getData().size();j++){
							PlansInfo planInfo = plansModel.get(i).getData().get(j);
							if(Const.NO.equals(planInfo.getStatus())){
								plansInfo.add(planInfo);
							}
						}
					}
					if(ValidatorUtil.isValidList(plansInfo)){
						isShowContent(true);
						showContent();
					}else
						isShowEmpty(true);
				}else{
					isShowEmpty(true);
				}
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
	private void showContent(){
		if (0 < plansInfo.size()) {
			JiHuaFragment.isNullData = false;
		}
		adapter = new LouPanQingDanListItemAdapter(plansInfo, inflater, LouPanActivity.this);
		adapter.setListener(new OnSlideListener() {

			@Override
			public void onSlide(View view, int status) {
			}
			@Override
			public void onClickRightComponent(View view) {
				int position = (Integer) view.getTag();
				alertDelHint(plansInfo.get(position));
			}

			@Override
			public void onClickLeftComponent(View view) {
			}
		});
		listView.setAdapter(adapter);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		closeBar();
		ToastUtil.show(activity, activity.getString(R.string.hint_error_net));
	}

	public void showCourse(int top, int left, int height) {
		initPopuWindowSlectDate(top, left, height);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt("清单");
		setLeftTitle("计划");
		setEmptyPage(R.drawable.ic_plan_empty, R.string.hint_no_plans);
		preferenceUtils = PreferenceUtils.getInstance(activity);
		inflater = LayoutInflater.from(this);
		listView = (SlideListView) findViewById(R.id.houselist);
		listView.setLockLeft(false);
		listView.setLockRight(true);
//		Utils.addHeaderAndFooterCutline(listView);
		getLouPan();
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return LouPanActivity.class.getSimpleName();
	}
	private void alertDelHint(final PlansInfo planInfo){
		if(planInfo == null)
			return;
		final AlertWidget alert = new AlertWidget(this);
		alert.showCustom(planInfo.getBuildingName(), "确定要删除该计划吗？", null, null, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				delQingDan(planInfo);
				alert.close();
			}
		});
		alert.show();
	}
	private void delQingDan(final PlansInfo planInfo) {
		showBar();
		setBarCancelable(false);
		Map<String,String> paramsContent = new HashMap<String,String>();
		paramsContent.put(Const.PARAM_PLANID, planInfo.getPlanId());
		paramsContent.put(Const.PARAM_ISVALID, Const.NO);
		HttpRequest.post(activity, Const.URL_PLAN_M, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				closeBar();
				plansInfo.remove(planInfo);
				if(!ValidatorUtil.isValidString(planInfo.getAppointmentTime())){
					PreferenceUtils.getInstance().minusUnappointplancount();
					EventBus.getDefault().post(new UnPlan());
				}
				adapter.notifyDataSetChanged();
				if(!ValidatorUtil.isValidList(plansInfo)){
					JiHuaFragment.isNullData = true;
					isShowEmpty(true);
				}
				EventBus.getDefault().post(new RemovePlan(planInfo.getBuildingId()));
				MainActivity.INSTANCE.onlyGetPlanData();
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

	private void initPopuWindow() {
		if (popuWindow1 == null) {
			contentView1 = inflater.inflate(R.layout.popuwindow_course_setloupanqingdan_back, null);
			popuWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			ImageView imgTop = (ImageView) contentView1.findViewById(R.id.imgTop);
			RelativeLayout.LayoutParams params = (LayoutParams) imgTop.getLayoutParams();
			int informationBarheight = getInformationBarheight();
			params.height = informationBarheight >= 30 ? informationBarheight - 30
					: informationBarheight - 10 > 0 ? informationBarheight - 10 : informationBarheight;
			imgTop.setLayoutParams(params);
			contentView1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					popuWindow1.dismiss();
				}
			});
		}
		ColorDrawable cd = new ColorDrawable(0x000000);
		popuWindow1.setBackgroundDrawable(cd);
		// 产生背景变暗效果
		popuWindow1.setOutsideTouchable(true);
		popuWindow1.setFocusable(true);
		popuWindow1.showAtLocation(this.findViewById(R.id.houselist), Gravity.TOP, 0, 0);
		popuWindow1.update();
	}

//	private void UpdateLouPan() {
//		HttpRequest.get(this, Const.URL_NAME_OF_HOUSES, getRequestTag(), null, Utils.getHeaderParamsOnly(),
//				new RequestListener() {
//
//					@Override
//					public void onSuccess(String url, String responseResult) {
//						NameOfHousesResponse mNameOfHousesResponse = new Gson().fromJson(responseResult,
//								NameOfHousesResponse.class);
//						ZhaoFangFragment.loupanList = mNameOfHousesResponse.getDataList();
//					}
//
//					@Override
//					public void onError(String url, String responseResult) {
//					}
//
//					@Override
//					public void onFailure(String url, String errorInfo) {
//					}
//
//				});
//	}

	public void initPopuWindowSlectDate(int top, int left, int height) {
		if (popuWindow == null) {
			contentView = inflater.inflate(R.layout.popwindow_course_setloupanqingdan_showdate, null);
			View viewRight = contentView.findViewById(R.id.viewRight);
			View viewTop = contentView.findViewById(R.id.viewTop);
			View viewCenter = contentView.findViewById(R.id.viewCenter);
			RelativeLayout.LayoutParams paramsViewTop = (LayoutParams) viewTop.getLayoutParams();
			paramsViewTop.height = top - height / 3;
			viewTop.setLayoutParams(paramsViewTop);

			LinearLayout.LayoutParams paramsViewCenter = (android.widget.LinearLayout.LayoutParams) viewCenter
					.getLayoutParams();
			paramsViewCenter.width = left - height / 2;
			paramsViewCenter.height = height + height / 2;
			viewCenter.setLayoutParams(paramsViewCenter);

			RelativeLayout.LayoutParams paramsViewRight = (LayoutParams) viewRight.getLayoutParams();
			paramsViewRight.width = Utils.getDeviceWidth(activity) - (left - height / 2);
			viewRight.setLayoutParams(paramsViewRight);

			popuWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			contentView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					initPopuWindow();
					popuWindow.dismiss();
				}
			});
		}
		ColorDrawable cd = new ColorDrawable(0x000000);
		popuWindow.setBackgroundDrawable(cd);
		// 产生背景变暗效果
		popuWindow.setOutsideTouchable(true);
		popuWindow.setFocusable(true);
		popuWindow.showAtLocation(this.findViewById(R.id.houselist), Gravity.NO_GRAVITY, 0, 0);
		popuWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				if ("0".equals(preferenceUtils.getIsFirstLouPanIsData())) {
					// initPopuWindow();
					preferenceUtils.setIsFirstLouPanIsData("1");
				}
			}
		});
	}
}
