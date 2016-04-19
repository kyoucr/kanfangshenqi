package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.Remind;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
/**
 ********************
 * 提醒列表适配器
 * @author cn
 ********************
 */
public class RemindAdp extends BaseAbsAdp {
	private List<Remind> list;
	private Context context;
	public RemindAdp(Context context, List<Remind> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public List<?> onGetListData() {
		return list;
	}

	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_remind, null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.imgHead = (CircleImageViewWithBorder) view.findViewById(R.id.imgHead);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		Remind remind = list.get(position);
		if(remind!=null){
			TextViewWriterUtil.writeValue(viewHolder.txtName, remind.getNickName());
			TextViewWriterUtil.writeValue(viewHolder.txtContent, remind.getContent());
			TextViewWriterUtil.writeValue(viewHolder.txtTime, remind.getCreateTime());
			ImageLoaderUtil.getInstance().bindHeadImg(viewHolder.imgHead, remind.getHeadPortrait());
		}
		return view;
	}

	class ViewHolder {
		CircleImageViewWithBorder imgHead;
		TextView txtName;
		TextView txtContent;
		TextView txtTime;
	}
}
