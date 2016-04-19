package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.BuildingDetailInfoActivity;
import com.xinwei.kanfangshenqi.model.HouseType;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class HouseTypeAdp extends BaseAbsAdpWithViewHolder {
	private List<HouseType> list;
	private Activity activity;
	public HouseTypeAdp(Activity activity, List<?> list) {
		super(activity, list, R.layout.adp_house_style);
		this.list = (List<HouseType>) list;
		this.activity = activity;
	}

	@Override
	public void onGetView(final int position, ViewHolder holder) {
		if (list.get(position) != null) {
			final ImageView img = holder.getView(R.id.imgHouseType);
//			img.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//				@Override
//				public void onGlobalLayout() {
//					if (img.getHeight() > 0) {
//						img.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//						ImageModel imgModel = new ImageModel();
//						imgModel.setHeight(img.getHeight());
//						imgModel.setWidth(img.getWidth());
//						imgModel.setPosition(position);
//						imgModel.setUrl(list.get(position).getImgPath());
//						int[] location = new int[2];
//						img.getLocationOnScreen(location);
//						imgModel.setX(location[0]);
//						imgModel.setY(location[1]);
//						imgModelMap.put(position, imgModel);
//					}
//				}
//			});
			ImageLoaderUtil.getInstance().bindImgCenterCrop(img, list.get(position).getImgSmallPath());
			TextView txt = holder.getView(R.id.txtDesc);
			TextViewWriterUtil.writeValue(txt, list.get(position).getImgDes());
			img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					BuildingDetailInfoActivity activityBuildingDetailInfo = (BuildingDetailInfoActivity) activity;
					activityBuildingDetailInfo.showImgPop(position);
//					Bundle data = new Bundle();
//					data.putSerializable(ImageModel.class.getSimpleName(), (Serializable) imgModelMap);
//					data.putInt("index", position);
//					Utils.moveTo(activity, SpaceImageDetailActivity.class, false, data);
				}
			});
		}
	}

}
