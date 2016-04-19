package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xinwei.kanfangshenqi.view.AlertWidget;

public abstract class BaseAbsAdp extends BaseAdapter {
	private AlertWidget bar;
	private List<?> list;

	public int getCount() {
		if (list == null)
			list = onGetListData();
		return list == null ? 0 : list.size();
	}

	public Object getItem(int arg0) {
		if (list == null)
			list = onGetListData();
		return list == null ? null : list.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int position, View view, ViewGroup arg2) {
		view = onGetView(position, view);
		if (view == null)
			throw new RuntimeException(
					"the returned value of onGetView is null");
		return view;
	}

	public abstract List<?> onGetListData();

	public abstract View onGetView(int position, View view);

	public void showBar(Context context) {
		if (bar == null && context != null)
			bar = new AlertWidget(context);
		if (bar != null)
			bar.showBar();
	}

	public void closeBar() {
		if (bar != null)
			bar.close();
	}
}
