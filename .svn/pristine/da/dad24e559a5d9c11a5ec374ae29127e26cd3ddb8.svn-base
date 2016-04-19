package com.xinwei.kanfangshenqi.activity;

import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.NotifyAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.NotifyList;
import com.xinwei.kanfangshenqi.model.NotifyList.Notify;
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
 * 通知
 * @author cn
 ********************
 */
public class NotifyActivity extends BaseActivity implements OnRefreshListener2{
	@ViewInject(R.id.lViNotify)
	private PullToRefreshListView lViNotify;
	private int beginPage = 1;
	private List<Notify> notifyDataList;
	private NotifyAdp adp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_notify);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.title_notify);
		setLeftTitle(R.string.tab_bar_ic_wode);
		lViNotify.setOnRefreshListener(this);
		Utils.addHeaderAndFooterCutline(lViNotify.getRefreshableView());
		setEmptyPage(R.drawable.ic_notify_empty, R.string.hint_notify_empty);
		onReloadData();
	}
	@Event(value=R.id.lViNotify,type=OnItemClickListener.class)
	private void toDetailEvent(AdapterView<?> arg0, View arg1, int arg2,
			long arg3){
		try {
			Notify notify = (Notify) arg0.getAdapter().getItem(arg2);
			Bundle data = new Bundle();
			data.putString(NotifyDetailInfoActivity.class.getSimpleName(), String.valueOf(notify.getPushId()));
			Utils.moveTo(activity, NotifyDetailInfoActivity.class, false, data);
		} catch (Exception e) {
		}
	}
	@Override
	public void onReloadData() {
		showBar();
		HttpRequest.get(activity, Const.URL_NOTIFY+String.valueOf(beginPage), getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				NotifyList notifyList = new Gson().fromJson(responseResult, NotifyList.class);
				notifyDataList = notifyList.getDataList();
				if(!ValidatorUtil.isValidList(notifyDataList)){
					isShowEmpty(true);
					return;
				}
				adp = new NotifyAdp(activity,notifyDataList);
				lViNotify.setAdapter(adp);
				Utils.judgePullRefreshStatus(lViNotify, beginPage, notifyList.getPageCount());
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
		return NotifyActivity.class.getSimpleName();
	}


	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		HttpRequest.get(activity, Const.URL_NOTIFY+String.valueOf(beginPage), getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				NotifyList notifyList = new Gson().fromJson(responseResult, NotifyList.class);
				notifyDataList = notifyList.getDataList();
				if(!ValidatorUtil.isValidList(notifyDataList)){
					isShowEmpty(true);
					return;
				}
				adp = new NotifyAdp(activity,notifyDataList);
				lViNotify.setAdapter(adp);
				Utils.judgePullRefreshStatus(lViNotify, beginPage, notifyList.getPageCount());
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				lViNotify.onRefreshComplete(false);
			}
			
			@Override
			public void onError(String url, String responseResult) {
				lViNotify.onRefreshComplete(false);
			}
		},true);
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		HttpRequest.get(activity, Const.URL_NOTIFY+String.valueOf(mNextPage), getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
			@Override
			public void onSuccess(String url, String responseResult) {
				NotifyList notifyList = new Gson().fromJson(responseResult, NotifyList.class);
				notifyDataList.addAll(notifyList.getDataList());
				adp.notifyDataSetChanged();
				Utils.judgePullRefreshStatus(lViNotify, mNextPage, notifyList.getPageCount());
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				lViNotify.onRefreshComplete(false);
			}
			
			@Override
			public void onError(String url, String responseResult) {
				lViNotify.onRefreshComplete(false);
			}
		},true);
	}

}
