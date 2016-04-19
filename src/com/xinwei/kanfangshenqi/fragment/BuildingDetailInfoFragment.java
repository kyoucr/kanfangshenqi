package com.xinwei.kanfangshenqi.fragment;

import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.BuildingCommentActivity;
import com.xinwei.kanfangshenqi.activity.BuildingDetailInfoActivity;
import com.xinwei.kanfangshenqi.adapter.LouPanAdapter;
import com.xinwei.kanfangshenqi.model.DataList;
import com.xinwei.kanfangshenqi.model.Interest;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.ListViewInScroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class BuildingDetailInfoFragment extends BaseFragment{
	@ViewInject(R.id.lViBuildingInfo)
	private ListViewInScroll lViBuildingInfo;
	
	private List<DataList> dataLists;
	
	public BuildingDetailInfoFragment(List<DataList> dataLists) {
		this.dataLists = dataLists;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_building_detail_info);
	}
	@Override
	public void onChildViewLoaded() {
		parentView.setBackgroundResource(android.R.color.white);
		setEmptyPage(0, R.string.no_data);
		if(ValidatorUtil.isValidList(dataLists)){
			LouPanAdapter adapter = new LouPanAdapter(dataLists);
			lViBuildingInfo.setAdapter(adapter);
		}else{
			isShowEmpty(true);
		}
	}

	@Override
	public void onReloadData() {
		
	}
	@Event(value={R.id.lViBuildingInfo},type=OnItemClickListener.class)
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
		return BuildingDetailInfoFragment.class.getSimpleName();
	}

}
