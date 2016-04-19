package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public abstract class BaseAbsAdpWithViewHolder extends BaseAdapter{
    private List<?> list;
    private Context context;
    private int layoutId;
    public BaseAbsAdpWithViewHolder(Context context,List<?> list,int layoutId) {
    	this.context = context;
    	this.list = list;
    	this.layoutId = layoutId;
	}
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
    @Override
    public Object getItem(int arg0) {
        return list==null?null:list.get(arg0);
    }
    @Override
    public long getItemId(int arg0) {
        return arg0;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
    	ViewHolder holder = ViewHolder.getViewHolder(context, view, parent, layoutId);
    	onGetView(position,holder);
    	view = holder.getMConvertView();
    	if(view == null)
    		throw new RuntimeException("the returned value of onGetView is null");
        return view;
    }
    public abstract void onGetView(int position, ViewHolder holder);
}
