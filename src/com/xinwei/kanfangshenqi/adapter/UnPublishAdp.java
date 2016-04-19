package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.UnPublishList.UnPublish;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/**
 ********************
 * 待发表列表适配器
 * @author cn
 ********************
 */
public class UnPublishAdp extends BaseAbsAdp {
	private Context context;
	private List<UnPublish> list;

	public UnPublishAdp(Context context, List<UnPublish> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.adp_unpublish,
					null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.img = (ImageView) view.findViewById(R.id.img);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		UnPublish unPublish = list.get(position);
		if (unPublish != null) {
			TextViewWriterUtil.writeValue(viewHolder.txtName,
					unPublish.getBuildingName());
			TextViewWriterUtil.writeValue(
					viewHolder.txtTime,
					unPublish.getPlanTime() != null ? context.getString(
							R.string.txt_see_house_time,
							unPublish.getPlanTime()) : null);
			ImageLoaderUtil.getInstance().bindImgFixXY(viewHolder.img, unPublish.getSmallBuildingImg());
		}
		return view;
	}

	class ViewHolder {
		TextView txtName;
		TextView txtTime;
		ImageView img;
	}
}
