package com.xinwei.kanfangshenqi.fragment;

import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.adapter.PublishedAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.PublishedList;
import com.xinwei.kanfangshenqi.model.PublishedList.Published;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 ******************** 
 * 已发表
 * 
 * @author cn
 ******************** 
 */
public class PublishedFragment extends BaseFragment implements
		OnRefreshListener2 {
	@ViewInject(R.id.lViPublished)
	private PullToRefreshListView lViPublished;
	private int beginPage = 1;
	private PublishedAdp publishedAdp;
	private List<Published> publishedDataList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_published);
	}

	@Override
	protected void onVisible() {
		super.onVisible();
		if (!isPrepared || !isVisible || mHasLoadedOnce) {
			return;
		}
		onReloadData();
	}
	@Override
	public void onChildViewLoaded() {
		lViPublished.setOnRefreshListener(this);
		setEmptyPage(R.drawable.ic_issue_empty, R.string.hint_see_house_plan_empty);
		onVisible();
	}

	@Override
	public void onReloadData() {
		mHasLoadedOnce = true;
		showBar();
		HttpRequest.get(activity,
				Const.URL_PUBLISHED + String.valueOf(beginPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						PublishedList publishedList = new Gson().fromJson(
								responseResult, PublishedList.class);
						publishedDataList = publishedList.getDataList();
						if(!ValidatorUtil.isValidList(publishedDataList)){
							isShowEmpty(true);
							return;
						}
						publishedAdp = new PublishedAdp(activity,
								publishedDataList);
						lViPublished.setAdapter(publishedAdp);
						Utils.judgePullRefreshStatus(lViPublished, beginPage,
								publishedList.getPageCount());
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
	@Event(value=R.id.lViPublished,type=OnItemClickListener.class)
	private void toDetailEvent(AdapterView<?> arg0, View arg1, int arg2,
			long arg3){
		try {
			Published published = (Published) arg0.getAdapter().getItem(arg2);
			Bundle data = new Bundle();
			data.putString(AskDetailNewActivity.class.getSimpleName(), published.getCommentId());
			data.putBoolean(Const.URL_BUILDING_COMMENT_DETAIL, true);
			data.putString(Const.WEB_LEFT_TITLE_KEY, getString(R.string.title_publish_gift));
			Utils.moveTo(activity, AskDetailNewActivity.class, false, data);
		} catch (Exception e) {
		}
	}
	@Override
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public String getRequestTag() {
		return PublishedFragment.class.getSimpleName();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		HttpRequest.get(activity,
				Const.URL_PUBLISHED + String.valueOf(beginPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						PublishedList publishedList = new Gson().fromJson(
								responseResult, PublishedList.class);
						publishedDataList = publishedList.getDataList();
						if(!ValidatorUtil.isValidList(publishedDataList)){
							isShowEmpty(true);
							return;
						}
						publishedAdp = new PublishedAdp(activity,
								publishedDataList);
						lViPublished.setAdapter(publishedAdp);
						Utils.judgePullRefreshStatus(lViPublished, beginPage,
								publishedList.getPageCount());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						lViPublished.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						lViPublished.onRefreshComplete(false);
					}
				},true);		
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		HttpRequest.get(activity,
				Const.URL_PUBLISHED + String.valueOf(mNextPage),
				getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
					@Override
					public void onSuccess(String url, String responseResult) {
						PublishedList publishedList = new Gson().fromJson(
								responseResult, PublishedList.class);
						publishedDataList.addAll(publishedList
								.getDataList());
						Utils.judgePullRefreshStatus(lViPublished, mNextPage,
								publishedList.getPageCount());
						publishedAdp.notifyDataSetChanged();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						lViPublished.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						lViPublished.onRefreshComplete(false);
					}
				},true);
	}

}
