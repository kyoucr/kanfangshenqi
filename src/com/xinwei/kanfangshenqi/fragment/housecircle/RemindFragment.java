package com.xinwei.kanfangshenqi.fragment.housecircle;

import java.util.ArrayList;
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
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.adapter.RemindAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Remind;
import com.xinwei.kanfangshenqi.model.SeeHouseCircle;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class RemindFragment extends BaseFragment implements
		OnRefreshListener2 {
	@ViewInject(R.id.pullToRefresh)
	private PullToRefreshListView pullToRefresh;
	private RemindAdp adp;
	private List<Remind> remindList;
	private String requestTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_see_house_circle);
	}

	@Event(value=R.id.pullToRefresh,type=OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		try {
			if (Utils.getHeaderParamsOnly() != null) {
				Remind remind = (Remind) arg0.getAdapter().getItem(arg2);
				Bundle data = new Bundle();
				if(!"chat".equals(remind.getType())){
					data.putBoolean(Const.URL_BUILDING_COMMENT_DETAIL, true);
				}
				data.putString(AskDetailNewActivity.class.getSimpleName(),
						remind.getCommentId());
				Utils.moveTo(activity, AskDetailNewActivity.class, false, data);
			}
		} catch (Exception e) {
		}
	}
	@Override
	public boolean isControlling() {
		if(remindList == null || remindList.size() == 0)
			return false;
        if (pullToRefresh.getRefreshableView().getFirstVisiblePosition() == 0) {
            View view = pullToRefresh.getRefreshableView().getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return false;
            }
        }
        return true;
	}
	@Override
	public void onReloadData() {
		showBar();
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.REMIND_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mBeginPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						remindList = shc.getRemindList();
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());

						if(!ValidatorUtil.isValidList(remindList)){
							showEmpty();
							return;
						}
						adp = new RemindAdp(activity, remindList);
						pullToRefresh.setAdapter(adp);
						Utils.judgePullLoadStatus(pullToRefresh, mBeginPage,
								shc.getPageCount());
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

	@Override
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public String getRequestTag() {
		return RemindFragment.class.getSimpleName();
	}

	@Override
	public void onChildViewLoaded() {
		requestTime = Utils.getSeeHouseCircleRequestTime();
		pullToRefresh.setOnRefreshListener(this);
		onReloadData();
	}

	private void showEmpty(){
		showCustom(R.layout.fragment_remind_empty);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, final int mBeginPage) {
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.REMIND_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mBeginPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						remindList = shc.getRemindList();
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());
						if(!ValidatorUtil.isValidList(remindList)){
							showEmpty();
							return;
						}
						adp = new RemindAdp(activity, remindList);
						pullToRefresh.setAdapter(adp);
						Utils.judgePullLoadStatus(pullToRefresh, mBeginPage,
								shc.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						pullToRefresh.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						pullToRefresh.onRefreshComplete(false);
					}
				});
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, int mNextPage) {
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.REMIND_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mNextPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						if (ValidatorUtil.isValidList(shc.getRemindList())) {
							remindList.addAll(shc.getRemindList());
							adp.notifyDataSetChanged();
						}
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());
						Utils.judgePullLoadStatus(pullToRefresh, mBeginPage,
								shc.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						pullToRefresh.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						pullToRefresh.onRefreshComplete(false);
					}
				});		
	}

}