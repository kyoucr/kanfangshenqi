package com.xinwei.kanfangshenqi.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 ********************
 * �������е�����viewholder
 * @author cn
 ********************
 */
public class ViewHolder {
	private SparseArray<View> mViews;
	private View mConvertView;
	public ViewHolder(Context context,ViewGroup parent,int layoutId) {
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
		mConvertView.setTag(this);
	}
	public static ViewHolder getViewHolder(Context context,View convertView,ViewGroup parent,int layoutId){
		if(convertView == null)
			return new ViewHolder(context,parent,layoutId);
		return (ViewHolder) convertView.getTag();
	}
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId){
		View item = mViews.get(viewId);
		if(item == null){
			item = mConvertView.findViewById(viewId);
			mViews.put(viewId, item);
		}
		return (T) item;
	}
	public View getMConvertView(){
		return mConvertView;
	}
}
