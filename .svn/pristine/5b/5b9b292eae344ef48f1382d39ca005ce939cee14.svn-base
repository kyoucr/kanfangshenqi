package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Ask.ImgAsk;
import com.xinwei.kanfangshenqi.util.DensityUtil;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ImgAdp extends BaseAbsAdp {
	private List<ImgAsk> list;
	private Context context;
	private int height = 0;

	public ImgAdp(Context context, List<ImgAsk> list) {
		this.context = context;
		this.list = list;
		height = (int) (((Utils.getDeviceWidth((Activity) context) - DensityUtil
				.dip2px(context, 30 + 35 + 10+45)) / 3));
	}

	@Override
	public List<?> onGetListData() {
		return list;
	}

	ViewHolder viewHolder;

	@Override
	public View onGetView(final int position, View view) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_img, null);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) view.findViewById(R.id.img);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		ImgAsk imgAsk = list.get(position);
		RelativeLayout.LayoutParams params = (LayoutParams) viewHolder.img
				.getLayoutParams();
		params.height = height;
		viewHolder.img.setLayoutParams(params);
		if (imgAsk != null) {
			ImageLoaderUtil.getInstance().bindImgCenterCrop(viewHolder.img, imgAsk.getCommentImgSmallPath());
			viewHolder.img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AskDetailNewActivity askDetailNewActivity = (AskDetailNewActivity) context;
					askDetailNewActivity.showPop(position);
				}
			});
		}
		return view;
	}

	class ViewHolder {
		ImageView img;
	}
}
