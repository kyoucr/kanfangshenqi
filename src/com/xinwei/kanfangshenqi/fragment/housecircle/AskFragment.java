package com.xinwei.kanfangshenqi.fragment.housecircle;

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
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.adapter.AskAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Ask;
import com.xinwei.kanfangshenqi.model.SeeHouseCircle;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AskFragment extends BaseFragment implements OnRefreshListener2<ListView> {
	@ViewInject(R.id.pullToRefresh)
	private PullToRefreshListView pullToRefresh;
	private AskAdp adp;
	private String requestTime;
	private List<Ask> askList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		addChildView(R.layout.fragment_see_house_circle);
	}
	@Subscribe
	public void onAskReplyEvent(Ask askDetail){
		if(ValidatorUtil.isValidList(askList)){
			for(Ask ask:askList){
				if(ask.getCommentId().equals(askDetail.getCommentId())){
					ask.setCommentCount(String.valueOf(askDetail.getReplayDataCount()));
					break;
				}
			}
			adp.notifyDataSetChanged();
		}
	}
	@Override
	public void onReloadData() {
		showBar();
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.ASK_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mBeginPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						askList = shc.getChatList();
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());
						adp = new AskAdp(activity, askList);
						pullToRefresh.setAdapter(adp);
						Utils.judgePullRefreshStatus(pullToRefresh, mBeginPage, shc.getPageCount());
//						Utils.judgeListViewStatus(listView, beginPage,
//								shc.getPageCount());
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
	public boolean isControlling() {
		if (askList == null || askList.size() == 0)
			return false;
		try {
			if(((SeeHouseCircleActivity)getActivity()).stickyLayout.getHeaderHeight()>0){
				return true;
			}
		} catch (Exception e) {
		}
		if (pullToRefresh.getRefreshableView().getFirstVisiblePosition() == 0) {
            View view = pullToRefresh.getRefreshableView().getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return false;
            }
        }
        return true;
	}


	@Override
	public String getRequestTag() {
		return AskFragment.class.getSimpleName();
	}

	@Override
	public void onChildViewLoaded() {
		pullToRefresh.setOnRefreshListener(this);
//		pullToRefresh.isShowHeader(false);
//		pullToRefresh.setMode(Mode.BOTH);
		requestTime = Utils.getSeeHouseCircleRequestTime();
		onReloadData();
	}

	@Event(value=R.id.pullToRefresh,type=OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		try {
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Ask ask = (Ask) arg0.getAdapter().getItem(arg2);
			Bundle data = new Bundle();
			data.putString(AskDetailNewActivity.class.getSimpleName(),
					ask.getCommentId());
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.title_ask_detail));
			Utils.moveTo(activity, AskDetailNewActivity.class, false, data);
		} catch (Exception e) {
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, final int mBeginPage) {
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.ASK_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mBeginPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						askList = shc.getChatList();
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());
						adp = new AskAdp(activity, askList);
						pullToRefresh.setAdapter(adp);
						Utils.judgePullRefreshStatus(pullToRefresh, mBeginPage, shc.getPageCount());
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
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		List<Map<String, String>> paramsContent = new ArrayList<Map<String, String>>();
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put(Const.PARAM_LASTTIME, requestTime);
		Map<String, String> paramsMap2 = new HashMap<String, String>();
		paramsMap2.put(Const.PARAM_TYPE, Const.ASK_TYPE);
		Map<String, String> paramsMap3 = new HashMap<String, String>();
		paramsMap3.put(Const.PARAM_CURPAGE, String.valueOf(mNextPage));
		paramsContent.add(paramsMap1);
		paramsContent.add(paramsMap2);
		paramsContent.add(paramsMap3);
		HttpRequest.rest(activity, Const.URL_SEE_HOUSE_CIRCLE, getRequestTag(),
				paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						SeeHouseCircle shc = new Gson().fromJson(
								responseResult, SeeHouseCircle.class);
						Utils.judgePullRefreshStatus(pullToRefresh, mNextPage, shc.getPageCount());
						if (shc.getChatList() != null
								&& shc.getChatList().size() > 0) {
							askList.addAll(shc.getChatList());
							adp.notifyDataSetChanged();
						}
						((SeeHouseCircleActivity) getActivity())
								.setNavigationNum(shc.getChatCount(),
										shc.getFollowCount(),
										shc.getRemindCount());
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
	public void onDestroy() {
		super.onDestroy();
		try {
			EventBus.getDefault().unregister(this);
		} catch (Exception e) {
		}
	}

}