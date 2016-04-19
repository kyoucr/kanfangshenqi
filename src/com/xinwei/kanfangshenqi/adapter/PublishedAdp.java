package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.PublishedList.Published;
import com.xinwei.kanfangshenqi.model.PublishedList.Published.Image;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/**
 ********************
 * 已发表列表适配器
 * @author cn
 ********************
 */
public class PublishedAdp extends BaseAbsAdp {
	private Context context;
	private List<Published> list;
	public PublishedAdp(Context context, List<Published> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.adp_published,
					null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtContent = (TextView) view
					.findViewById(R.id.txtContent);
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.img1 = (ImageView) view.findViewById(R.id.img1);
			viewHolder.img2 = (ImageView) view.findViewById(R.id.img2);
			viewHolder.img3 = (ImageView) view.findViewById(R.id.img3);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		Published published = list.get(position);
		if (published != null) {
			TextViewWriterUtil.writeValue(viewHolder.txtName,
					published.getBuildingName());
			TextViewWriterUtil.writeValue(viewHolder.txtContent,
					published.getContent());
			TextViewWriterUtil.writeValue(
					viewHolder.txtTime,
					published.getCreateTime() != null ? context.getString(
							R.string.txt_publish_time,
							published.getCreateTime()) : null);
			List<Image> imgList = published.getImgs();
			try {
				if(ValidatorUtil.isValidString(imgList.get(0).getPath())){
					viewHolder.img1.setVisibility(View.VISIBLE);
					ImageLoaderUtil.getInstance().bindImgFixXY(viewHolder.img1, imgList.get(0).getSmallPath());
				}else{
					viewHolder.img1.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				viewHolder.img1.setVisibility(View.GONE);
			}
			try {
				if(ValidatorUtil.isValidString(imgList.get(1).getPath())){
					viewHolder.img2.setVisibility(View.VISIBLE);
					ImageLoaderUtil.getInstance().bindImgFixXY(viewHolder.img2, imgList.get(1).getSmallPath());
				}else{
					viewHolder.img2.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				viewHolder.img2.setVisibility(View.GONE);
			}
			try {
				if(ValidatorUtil.isValidString(imgList.get(2).getPath())){
					viewHolder.img3.setVisibility(View.VISIBLE);
					ImageLoaderUtil.getInstance().bindImgFixXY(viewHolder.img3, imgList.get(2).getSmallPath());
				}else{
					viewHolder.img3.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				viewHolder.img3.setVisibility(View.GONE);
			}
			
		}
		return view;
	}

	class ViewHolder {
		TextView txtName;
		TextView txtContent;
		TextView txtTime;
		ImageView img1;
		ImageView img2;
		ImageView img3;
	}
}