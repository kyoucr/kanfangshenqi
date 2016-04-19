package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.king.photo.activity.PublishActivity;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.BuildingDetailAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.BuildingCommentPublish;
import com.xinwei.kanfangshenqi.model.BuildingDetail;
import com.xinwei.kanfangshenqi.model.BuildingDetail.Building;
import com.xinwei.kanfangshenqi.model.Interest;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 ********************
 * 楼盘详情
 * @author cn
 ********************
 */
public class BuildingCommentActivity extends BaseActivity implements OnRefreshListener2{
	private Interest interestPre;
	private int beginPage = 1;
	@ViewInject(R.id.imgHead)
	private CircleImageViewWithBorder imgHead;
	@ViewInject(R.id.lViInterest)
	private PullToRefreshListView lViInterest;
	private BuildingDetailAdp buildingDetailAdp;
	private List<Building> buildingList;
	@ViewInject(R.id.imgTop)
	private ImageView imgTop;
	@ViewInject(R.id.rltEmpty)
	private RelativeLayout rltEmpty;
	private BuildingDetail buildingDetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		addChildView(R.layout.activity_interest_detail);
	}

	@Override
	public void onChildViewLoaded() {
		interestPre = (Interest) getIntent().getSerializableExtra(
				Interest.class.getSimpleName());
		if(interestPre==null){
			finish();
			return;
		}
		String titleLeft = getIntent().getStringExtra(Const.WEB_LEFT_TITLE_KEY);
		if(ValidatorUtil.isValidString(titleLeft)){
			setLeftTitle(titleLeft);
		}
		String title = getIntent().getStringExtra(Const.WEB_TITLE_KEY);
		if(ValidatorUtil.isValidString(title)){
			setTitleTxt(title);
		}
//		setTitleTxt(interestPre.getBuildingName());
		lViInterest.setOnRefreshListener(this);
		imgBtnRight.setImageDrawable(getResources().getDrawable(R.drawable.copyreader_nav_button));
		imgBtnRight.setVisibility(View.VISIBLE);
		ImageLoaderUtil.getInstance().bindHeadImg(imgHead, PreferenceUtils.getInstance(activity).getSettingUserPic());
		ImageLoaderUtil.getInstance().bindImgFixXY(imgTop, interestPre.getBanner());
		onReloadData();
	}
	@Override
	public void onReloadData() {
		showBar();
		List<Map<String,String>> paramsContent = new ArrayList<Map<String,String>>();
		Map<String,String> paramMap1 = new HashMap<String,String>();
		paramMap1.put(Const.PARAM_BUILDING_ID, interestPre.getBuildingId());
		Map<String,String> paramMap2 = new HashMap<String,String>();
		paramMap2.put(Const.PARAM_cURPAGE, String.valueOf(beginPage));
		paramsContent.add(paramMap1);
		paramsContent.add(paramMap2);
		HttpRequest.rest(activity, Const.URL_INTEREST_DETAIL, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				buildingDetail = new Gson().fromJson(responseResult, BuildingDetail.class);
				Utils.judgePullRefreshStatus(lViInterest, beginPage, buildingDetail.getPageCount());
				buildingList = buildingDetail.getDataList();
				if(!ValidatorUtil.isValidList(buildingList)){
					rltEmpty.setVisibility(View.VISIBLE);
					lViInterest.setVisibility(View.GONE);
//					lViInterest.setVisibility(View.GONE);
					isShowContent(true);
					return;
				}
				rltEmpty.setVisibility(View.GONE);
				lViInterest.setVisibility(View.VISIBLE);
				buildingDetailAdp = new BuildingDetailAdp(activity, buildingList);
				lViInterest.setAdapter(buildingDetailAdp);
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
		});
	}
	@Event(value=R.id.lViInterest,type=OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> arg0, View arg1,
			int arg2, long arg3) {
		try {
			Building building = (Building) arg0.getAdapter().getItem(arg2);
			Bundle data = new Bundle();
			data.putString(AskDetailNewActivity.class.getSimpleName(), building.getCommentId());
			data.putBoolean(Const.URL_BUILDING_COMMENT_DETAIL, true);
			data.putString(Const.WEB_LEFT_TITLE_KEY, "楼盘评论");
//			data.putString(Const.WEB_TITLE_KEY,buildingDetail.getBuildingName());
			Utils.moveTo(activity, AskDetailNewActivity.class, false, data);
		} catch (Exception e) {
		}
	}
	@Event(R.id.imgBtnRight)
	private void commentEvent(View v){
		if(buildingDetail.getIsLook()){
			Bundle data = new Bundle();
			data.putString(Const.PARAM_BUILDING_ID, interestPre.getBuildingId());
			Utils.moveTo(activity, PublishActivity.class, false, data);
		}else{
			ToastUtil.show(activity, "您未看过此楼盘，不能发表");
		}
	}
	@Subscribe
	public void onBuildingCommentPublishEvent(BuildingCommentPublish buildingCommentPublish){
		onReloadData();
	}
	@Override
	public String getRequestTag() {
		return BuildingCommentActivity.class.getSimpleName();
	}



	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		List<Map<String,String>> paramsContent = new ArrayList<Map<String,String>>();
		Map<String,String> paramMap1 = new HashMap<String,String>();
		paramMap1.put(Const.PARAM_BUILDING_ID, interestPre.getBuildingId());
		Map<String,String> paramMap2 = new HashMap<String,String>();
		paramMap2.put(Const.PARAM_cURPAGE, String.valueOf(beginPage));
		paramsContent.add(paramMap1);
		paramsContent.add(paramMap2);
		HttpRequest.rest(activity, Const.URL_INTEREST_DETAIL, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				buildingDetail = new Gson().fromJson(responseResult, BuildingDetail.class);
				Utils.judgePullRefreshStatus(lViInterest, beginPage, buildingDetail.getPageCount());
				buildingList = buildingDetail.getDataList();
				if(!ValidatorUtil.isValidList(buildingList)){
					rltEmpty.setVisibility(View.VISIBLE);
					lViInterest.setVisibility(View.GONE);
					return;
				}
				rltEmpty.setVisibility(View.GONE);
				lViInterest.setVisibility(View.VISIBLE);
				buildingDetailAdp = new BuildingDetailAdp(activity, buildingList);
				lViInterest.setAdapter(buildingDetailAdp);
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				lViInterest.onRefreshComplete(false);
				
			}
			
			@Override
			public void onError(String url, String responseResult) {
				lViInterest.onRefreshComplete(false);
			}
		});		
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		List<Map<String,String>> paramsContent = new ArrayList<Map<String,String>>();
		Map<String,String> paramMap1 = new HashMap<String,String>();
		paramMap1.put(Const.PARAM_BUILDING_ID, interestPre.getBuildingId());
		Map<String,String> paramMap2 = new HashMap<String,String>();
		paramMap2.put(Const.PARAM_cURPAGE, String.valueOf(mNextPage));
		paramsContent.add(paramMap1);
		paramsContent.add(paramMap2);
		HttpRequest.rest(activity, Const.URL_INTEREST_DETAIL, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				isShowContent(true);
				BuildingDetail buildingDetail= new Gson().fromJson(responseResult, BuildingDetail.class);
				Utils.judgePullRefreshStatus(lViInterest, mNextPage, buildingDetail.getPageCount());
				buildingList.addAll(buildingDetail.getDataList());
				buildingDetailAdp.notifyDataSetChanged();
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				lViInterest.onRefreshComplete(false);
				
			}
			
			@Override
			public void onError(String url, String responseResult) {
				lViInterest.onRefreshComplete(false);
			}
		});		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			EventBus.getDefault().unregister(this);
		} catch (Exception e) {
		}
	}
}