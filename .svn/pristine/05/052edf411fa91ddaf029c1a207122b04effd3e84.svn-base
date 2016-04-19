package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.WebActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.EncyclopediaContent;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.ListViewInScroll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 *******************
 * 百科列表适配器
 * @author cn
 *
 *******************
 */
public class EncyclopediaAdp extends BaseAbsAdp {
	private List<EncyclopediaContent> list;
	private Activity context;

	public EncyclopediaAdp(Activity context, List<EncyclopediaContent> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public List<?> onGetListData() {
		return list;
	}

	private ViewHolder viewHolder;

	@Override
	public View onGetView(int position, View view) {
		final EncyclopediaContent encyclopediaContent = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_encyclopedia, null);
			viewHolder = new ViewHolder();
			viewHolder.txtStep = (TextView) view.findViewById(R.id.txtStep);
			viewHolder.txtStepDesc = (TextView) view.findViewById(R.id.txtStepDesc);
			viewHolder.txtCtrl = (TextView) view.findViewById(R.id.txtCtrl);
			viewHolder.imgCutline = (ImageView) view.findViewById(R.id.imgCutline);
			viewHolder.lViContent = (ListViewInScroll) view.findViewById(R.id.lViContent);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		TextViewWriterUtil.writeValue(viewHolder.txtStep, encyclopediaContent.getSerialNum());
		TextViewWriterUtil.writeValue(viewHolder.txtStepDesc, encyclopediaContent.getClassName());
		//根据flag，加载不同状态 
		if (encyclopediaContent.isShowAll()) {
			viewHolder.lViContent
					.setAdapter(new EncyclopediaSubContentAdp(context, encyclopediaContent.getDataList(), true));
			TextViewWriterUtil.writeValue(viewHolder.txtCtrl, context.getString(R.string.hide_more));
		} else {
			viewHolder.lViContent
					.setAdapter(new EncyclopediaSubContentAdp(context, encyclopediaContent.getDataList(), false));
			TextViewWriterUtil.writeValue(viewHolder.txtCtrl, context.getString(R.string.show_more));
		}
		//如果item数量小于2，隐藏显示更多功能
		if (encyclopediaContent.getDataList() == null || encyclopediaContent.getDataList().size() <= 2) {
			viewHolder.txtCtrl.setVisibility(View.GONE);
			viewHolder.imgCutline.setVisibility(View.GONE);
		} else {
			viewHolder.txtCtrl.setVisibility(View.VISIBLE);
			viewHolder.imgCutline.setVisibility(View.VISIBLE);
		}
		//修改flag后，刷新列表
		viewHolder.txtCtrl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (encyclopediaContent.isShowAll()) {
					encyclopediaContent.setShowAll(false);
				} else {
					encyclopediaContent.setShowAll(true);
				}
				notifyDataSetChanged();
			}
		});
		viewHolder.lViContent.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Bundle data = new Bundle();
				data.putString(Const.WEB_URL_KEY, Const.URL_ENCYCLOPEDIA_INFO+encyclopediaContent.getDataList().get(arg2).getEncyclopediaId());
				data.putString(Const.WEB_TITLE_KEY, encyclopediaContent.getDataList().get(arg2).getTitle());
				data.putString(Const.WEB_LEFT_TITLE_KEY, context.getString(R.string.encyclopedia));
				Utils.moveTo(context, WebActivity.class, false, data);
			}
		});
		return view;
	}

	class ViewHolder {
		TextView txtStep;
		TextView txtStepDesc;
		TextView txtCtrl;
		ImageView imgCutline;
		ListViewInScroll lViContent;
	}

}
