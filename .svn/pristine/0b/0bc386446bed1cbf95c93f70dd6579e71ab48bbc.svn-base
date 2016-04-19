package com.xinwei.kanfangshenqi.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.EncyclopediaAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Encyclopedia;
import com.xinwei.kanfangshenqi.model.EncyclopediaContent;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class EncyclopediaFragment extends BaseFragment implements OnRefreshListener2{
	@ViewInject(R.id.pullToRefreshListView)
	private PullToRefreshListView pullToRefreshListView;
	private EncyclopediaAdp adp;
	private String parentId;
	private boolean isNeedRequest = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShowTitle(false);
		addChildView(R.layout.fragment_encyclopedia);
	}

	public EncyclopediaFragment(Activity activity,String parentId) {
		this.activity = activity;
		this.parentId = parentId;
		isNeedRequest = true;
	}

	public EncyclopediaFragment(Activity activity , List<EncyclopediaContent> list) {
		this.activity = activity;
		adp = new EncyclopediaAdp(activity, list);
		isNeedRequest = false;
		mHasLoadedOnce = true;
	}
	@Override
	protected void onVisible() {
		if (!isPrepared || !isVisible || mHasLoadedOnce) {
			return;
		}
		onReloadData();
	}
	@Override
	public void onChildViewLoaded() {
		pullToRefreshListView.setOnRefreshListener(this);
		if(!isNeedRequest)
			pullToRefreshListView.setAdapter(adp);
		else
			onVisible();
			
	}

	@Override
	public void onReloadData() {
		mHasLoadedOnce = true;
		showBar();
		Map<String, String> params = new HashMap<String, String>();
		params.put(Const.PARAM_PARENTID, parentId);
		HttpRequest.get(activity, Const.URL_ENCYCLOPEDIACLASSES,
				getRequestTag(), params, null, new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						isShowContent(true);
						Encyclopedia encyclopedia = new Gson().fromJson(responseResult, Encyclopedia.class);
						adp = new EncyclopediaAdp(activity, encyclopedia.getSecondClassList());
						pullToRefreshListView.setAdapter(adp);
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
		return EncyclopediaFragment.class.getSimpleName();
	}


	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Const.PARAM_PARENTID, parentId);
		HttpRequest.get(activity, Const.URL_ENCYCLOPEDIACLASSES,
				getRequestTag(), params, null, new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						Encyclopedia encyclopedia = new Gson().fromJson(responseResult, Encyclopedia.class);
						adp = new EncyclopediaAdp(activity, encyclopedia.getSecondClassList());
						pullToRefreshListView.setAdapter(adp);
						pullToRefreshListView.onRefreshComplete(true);
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
	public void onPullUpToLoad(PullToRefreshBase refreshView, int mNextPage) {
		// TODO Auto-generated method stub
		
	}
}
