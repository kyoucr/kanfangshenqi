package com.xinwei.kanfangshenqi.fragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinwei.kanfangshenqi.BaseFragment2;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.EncyclopediaActivity;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.activity.ToolsActivity;
import com.xinwei.kanfangshenqi.activity.WonderfulActivityActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Discovery;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.XCRoundRectImageView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author cn
 */
public class FaxianFragment extends BaseFragment2 {
	@ViewInject(R.id.rltNew)
	private RelativeLayout rltNew;
	@ViewInject(R.id.imgHead)
	private XCRoundRectImageView imgHead;
	public static RelativeLayout rltNewUpdate;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		isShowTitle(true);
		isShowLeft(false);
		addChildView(R.layout.fragment_faxian);
		return parentView;
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.tab_bar_ic_faxian);
		rltNewUpdate = rltNew;
	}

	@Event({ R.id.lltEncyclopedia, R.id.lltWonderfulActivity,
			R.id.lltSeeHouseTools, R.id.lltSeeHouseCircle })
	private void toItemEvent(View v) {
		switch (v.getId()) {
		case R.id.lltEncyclopedia:
			Utils.moveTo(getActivity(), EncyclopediaActivity.class, false, null);
			break;
		case R.id.lltWonderfulActivity:
			Utils.moveTo(getActivity(), WonderfulActivityActivity.class, false,
					null);
			break;
		case R.id.lltSeeHouseTools:
			Utils.moveTo(getActivity(), ToolsActivity.class, false, null);
			break;
		case R.id.lltSeeHouseCircle:
			Bundle data = new Bundle();
			data.putSerializable(Discovery.class.getSimpleName(), discovery);
			Utils.moveTo(getActivity(), SeeHouseCircleActivity.class, false,
					data);
			break;
		}
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return FaxianFragment.class.getSimpleName();
	}
	private Discovery discovery;
	public void requestNewMsg() {
		
		HttpRequest.get(
				activity,
				Const.URL_DISCOVERY
						+ Utils.getSeeHouseCircleRequestTime(),
				getRequestTag(), null, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						discovery = new Gson().fromJson(
								responseResult, Discovery.class);
						if(ValidatorUtil.isValidString(discovery.getForwardType())){
							if(ValidatorUtil.isValidString(discovery.getReplyHeadPortrait()))
								ImageLoader.getInstance().displayImage(discovery.getReplyHeadPortrait(), imgHead);
							rltNew.setVisibility(View.VISIBLE);
						}else{
							rltNew.setVisibility(View.GONE);
						}
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						discovery = null;
					}

					@Override
					public void onError(String url, String responseResult) {
						discovery = null;
					}
				},true);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		rltNewUpdate = null;
	}
}
