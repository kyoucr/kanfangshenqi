package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.NotifyList.Notify;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
/**
 ********************
 * 通知列表适配器
 * @author cn
 ********************
 */
public class NotifyAdp extends BaseAbsAdp{
	private Context context;
	private List<Notify> notifyList;
	public NotifyAdp(Context context,List<Notify> notifyList) {
		this.context = context;
		this.notifyList = notifyList;
	}
	@Override
	public List<?> onGetListData() {
		return notifyList;
	}

	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.adp_notify, null);
			viewHolder = new ViewHolder();
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
			viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
			view.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) view.getTag();
		}
		Notify notify = notifyList.get(position);
		if(notify!=null){
			TextViewWriterUtil.writeValue(viewHolder.txtTitle, notify.getTitle());
			TextViewWriterUtil.writeValue(viewHolder.txtTime, notify.getPushTime());
			TextViewWriterUtil.writeValue(viewHolder.txtContent, notify.getContent());
			
		}
		return view;
	}
	class ViewHolder{
		TextView txtTime;
		TextView txtTitle;
		TextView txtContent;
	}

}
