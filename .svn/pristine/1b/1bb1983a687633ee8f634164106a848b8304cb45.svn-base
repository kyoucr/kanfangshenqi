package com.xinwei.kanfangshenqi.fragment;

import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.king.photo.activity.PublishActivity;
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity.OnPublishedListener;
import com.xinwei.kanfangshenqi.adapter.UnPublishAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.UnPublishList;
import com.xinwei.kanfangshenqi.model.UnPublishList.UnPublish;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 ******************** 
 * 未发表
 * 
 * @author cn
 ******************** 
 */
public class UnPublishFragment extends BaseFragment implements
	OnRefreshListener2 {
	@ViewInject(R.id.lViUnPublish)
	private PullToRefreshListView lViUnPublish;
	private int beginPage = 1;
	private UnPublishAdp unPublishAdp;
	private List<UnPublish> unPublishDataList;
	private OnPublishedListener onPublishedListener = new OnPublishedListener() {
		
		@Override
		public void onPulished() {
			onReloadData();
		}
	};
	public static OnPublishedListener OnPublishedListener;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_unpublish);
	}

	@Override
	public void onChildViewLoaded() {
		lViUnPublish.setBeginPage(beginPage);
		lViUnPublish.setOnRefreshListener(this);
		Utils.addHeaderAndFooterCutline(lViUnPublish.getRefreshableView());
		setEmptyPage(R.drawable.ic_issue_empty, R.string.hint_see_house_plan_empty);
		onVisible();
	}
	@Override
	protected void onVisible() {
		if (!isPrepared || !isVisible || mHasLoadedOnce) {
			return;
		}
		onReloadData();
	}
	@Override
	public void onReloadData() {
		mHasLoadedOnce = true;
		showBar();
		HttpRequest.get(activity,
				Const.URL_UNPUBLISH + String.valueOf(beginPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						UnPublishList unPublishList = new Gson().fromJson(
								responseResult, UnPublishList.class);
						unPublishDataList = unPublishList.getDataList();
						if(!ValidatorUtil.isValidList(unPublishDataList)){
							isShowEmpty(true);
							return;
						}
						unPublishAdp = new UnPublishAdp(activity,
								unPublishDataList);
						lViUnPublish.setAdapter(unPublishAdp);
						Utils.judgePullRefreshStatus(lViUnPublish, beginPage,
								unPublishList.getPageCount());
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
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public String getRequestTag() {
		return UnPublishFragment.class.getSimpleName();
	}
	@Event(value=R.id.lViUnPublish,type=OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> arg0, View arg1, int arg2,
			long arg3){
		try {
			UnPublish unPublish = (UnPublish) arg0.getAdapter().getItem(arg2);
			OnPublishedListener = onPublishedListener;
			Bundle data = new Bundle();
			data.putSerializable(UnPublish.class.getSimpleName(), unPublish);
			Intent intent = new Intent(activity,PublishActivity.class);
			intent.putExtras(data);
			Utils.moveTo(getActivity(), PublishActivity.class, false, data);
		} catch (Exception e) {
		}
	}
	@Override
	public void onDestroy() {
		OnPublishedListener = null;
		super.onDestroy();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		HttpRequest.get(activity,
				Const.URL_UNPUBLISH + String.valueOf(beginPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						UnPublishList unPublishList = new Gson().fromJson(
								responseResult, UnPublishList.class);
						unPublishDataList = unPublishList.getDataList();
						if(!ValidatorUtil.isValidList(unPublishDataList)){
							isShowEmpty(true);
							return;
						}
						unPublishAdp = new UnPublishAdp(activity,
								unPublishDataList);
						lViUnPublish.setAdapter(unPublishAdp);
						Utils.judgePullRefreshStatus(lViUnPublish, beginPage,
								unPublishList.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						lViUnPublish.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						lViUnPublish.onRefreshComplete(false);
					}
				},true);		
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		HttpRequest.get(activity,
				Const.URL_UNPUBLISH + String.valueOf(mNextPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
					@Override
					public void onSuccess(String url, String responseResult) {
						UnPublishList unPublishList = new Gson().fromJson(
								responseResult, UnPublishList.class);
						unPublishDataList.addAll(unPublishList
								.getDataList());
						Utils.judgePullRefreshStatus(lViUnPublish, mNextPage,
								unPublishList.getPageCount());
						unPublishAdp.notifyDataSetChanged();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						lViUnPublish.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						lViUnPublish.onRefreshComplete(false);
					}
				},true);		
	}

}
