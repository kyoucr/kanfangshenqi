package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.EncyclopediaData;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class EncyclopediaSubContentAdp extends BaseAbsAdp {
	private List<EncyclopediaData> dataList;
	private Context context;
	private boolean isShowAll;

	public EncyclopediaSubContentAdp(Context context, List<EncyclopediaData> dataList,boolean isShowAll) {
		this.context = context;
		this.dataList = dataList;
		this.isShowAll = isShowAll;
	}

	@Override
	public List<?> onGetListData() {
		return dataList;
	}

	@Override
	public int getCount() {
		if (isShowAll && dataList != null){
			return dataList.size();
		}else{
			return dataList == null ? 0 : dataList.size() > 2 ? 2 : dataList.size();
		}
	}
	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_encyclopedia_sub_content, null);
			viewHolder = new ViewHolder();
			viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		EncyclopediaData encyclopediaData = dataList.get(position);
		if (encyclopediaData != null) {
			TextViewWriterUtil.writeValue(viewHolder.txtContent, encyclopediaData.getTitle());
		}
		return view;
	}

	class ViewHolder {
		TextView txtContent;
	}

}
