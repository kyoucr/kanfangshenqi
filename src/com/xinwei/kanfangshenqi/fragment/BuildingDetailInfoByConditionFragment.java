package com.xinwei.kanfangshenqi.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.BuildingCommentActivity;
import com.xinwei.kanfangshenqi.activity.BuildingDetailInfoActivity;
import com.xinwei.kanfangshenqi.adapter.LouPanAdapter;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.common.Recommend;
import com.xinwei.kanfangshenqi.model.BuildingDetailInfo;
import com.xinwei.kanfangshenqi.model.DataList;
import com.xinwei.kanfangshenqi.model.FeatureList;
import com.xinwei.kanfangshenqi.model.Interest;
import com.xinwei.kanfangshenqi.model.RecommendBuildings;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class BuildingDetailInfoByConditionFragment extends BaseFragment implements OnRefreshListener2 {
	@ViewInject(R.id.pullToRefreshListView)
	private PullToRefreshListView pullToRefreshListView;

	private List<DataList> dataLists;
	private Map<String, String> paramsContent;
	private BuildingDetailInfo buildingInfo;
	private LouPanAdapter adpLouPan;

	public BuildingDetailInfoByConditionFragment(Recommend recommend, BuildingDetailInfo buildingInfo) {
		paramsContent = new HashMap<String, String>();
		paramsContent.put(Const.PARAM_ISPAGE, Const.YES);
		if (recommend == Recommend.AREA)
			paramsContent.put(Const.PARAM_AREA_ID, buildingInfo.getAreaId());
		else if (recommend == Recommend.FEATURE) {
			paramsContent.put(Const.PARAM_FEATURE_ID, getFeatureId());
		} else if (recommend == Recommend.PRICE) {
			paramsContent.put(Const.PARAM_PRICE_ID, buildingInfo.getPriceId());
		} else if (recommend == Recommend.SYNTHESIZE) {
			paramsContent.put(Const.PARAM_ISCOMPREHENSIVE, Const.YES);
			paramsContent.put(Const.PARAM_AREA_ID, buildingInfo.getAreaId());
			paramsContent.put(Const.PARAM_FEATURE_ID, getFeatureId());
			paramsContent.put(Const.PARAM_PRICE_ID, buildingInfo.getPriceId());
		}
	}

	private String getFeatureId() {
		String paramFeatrue = "";
		if (buildingInfo != null) {
			for (FeatureList feature : buildingInfo.getFeatures()) {
				paramFeatrue += feature.getFeatureId() + ",";
			}
			if (ValidatorUtil.isValidString(paramFeatrue))
				paramFeatrue = paramFeatrue.substring(0, paramFeatrue.length() - 1);
		}
		return paramFeatrue;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_building_detail_info_by_condition);
	}

	@Override
	public void onChildViewLoaded() {
		parentView.setBackgroundResource(android.R.color.white);
		setEmptyPage(0, R.string.no_data);
		pullToRefreshListView.setOnRefreshListener(this);
		onReloadData();
	}

	@Override
	public void onReloadData() {
		showBar();
		paramsContent.put(Const.PARAM_PAGENUM, String.valueOf(mBeginPage));
		HttpRequest.get(activity, Const.URL_BUILDINGS_BY_CONDITION, getRequestTag(), paramsContent,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						RecommendBuildings buildings = new Gson().fromJson(responseResult, RecommendBuildings.class);
						dataLists = buildings.getDataList();
						if (ValidatorUtil.isValidList(dataLists)) {
							adpLouPan = new LouPanAdapter(dataLists);
							pullToRefreshListView.setAdapter(adpLouPan);
							Utils.judgePullRefreshStatus(pullToRefreshListView, mBeginPage, buildings.getPageCount());
							isShowContent(true);
						} else {
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
				});
	}

	@Event(value = { R.id.pullToRefreshListView }, type = OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> parent, View view, int position, long id) {
		try {
			DataList dataList = (DataList) parent.getAdapter().getItem(position);
			Bundle data = new Bundle();
			data.putSerializable(DataList.class.getSimpleName(), dataList);
			Utils.moveTo(activity, BuildingDetailInfoActivity.class, false, data);
		} catch (Exception e) {
		}
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public String getRequestTag() {
		return BuildingDetailInfoByConditionFragment.class.getSimpleName();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, final int mBeginPage) {
		paramsContent.put(Const.PARAM_PAGENUM, String.valueOf(mBeginPage));
		HttpRequest.get(activity, Const.URL_BUILDINGS_BY_CONDITION, getRequestTag(), paramsContent,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						pullToRefreshListView.onRefreshComplete(true);
						RecommendBuildings buildings = new Gson().fromJson(responseResult, RecommendBuildings.class);
						dataLists = buildings.getDataList();
						adpLouPan = new LouPanAdapter(dataLists);
						pullToRefreshListView.setAdapter(adpLouPan);
						Utils.judgePullRefreshStatus(pullToRefreshListView, mBeginPage, buildings.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						pullToRefreshListView.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						pullToRefreshListView.onRefreshComplete(false);
					}
				});
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		paramsContent.put(Const.PARAM_PAGENUM, String.valueOf(mNextPage));
		HttpRequest.get(activity, Const.URL_BUILDINGS_BY_CONDITION, getRequestTag(), paramsContent,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						pullToRefreshListView.onRefreshComplete(true);
						RecommendBuildings buildings = new Gson().fromJson(responseResult, RecommendBuildings.class);
						if (ValidatorUtil.isValidList(buildings.getDataList())) {
							dataLists.addAll(buildings.getDataList());
							adpLouPan.notifyDataSetChanged();
						}
						Utils.judgePullRefreshStatus(pullToRefreshListView, mNextPage, buildings.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						pullToRefreshListView.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						pullToRefreshListView.onRefreshComplete(false);
					}
				});
	}

}
