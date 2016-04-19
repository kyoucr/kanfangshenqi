package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import org.xutils.common.util.DensityUtil;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.BuildingDetail.Building;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class BuildingDetailAdp extends BaseAbsAdp {
	private List<Building> list;
	private Context context;
	private int imgHeight;
	private int lltWidth;
	private LinearLayout.LayoutParams params;
	public BuildingDetailAdp(Context context, List<Building> list) {
		this.context = context;
		this.list = list;
		int imgWidth = (DensityUtil.getScreenWidth() - DensityUtil.dip2px(75))/3;
		lltWidth = DensityUtil.getScreenWidth() - DensityUtil.dip2px(65);
		imgHeight = (int)(imgWidth*Const.SCALE);
	}

	@Override
	public List<?> onGetListData() {
		return list;
	}

	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_ask, null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.txtPlan = (TextView) view.findViewById(R.id.txtPlan);
			viewHolder.txtNum = (TextView) view.findViewById(R.id.txtNum);
			viewHolder.img1 = (ImageView) view.findViewById(R.id.img1);
			viewHolder.img2 = (ImageView) view.findViewById(R.id.img2);
			viewHolder.img3 = (ImageView) view.findViewById(R.id.img3);
			viewHolder.imgHead = (CircleImageViewWithBorder) view.findViewById(R.id.imgHead);
			viewHolder.lltImgs = (LinearLayout) view.findViewById(R.id.lltImgs);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		Building building = list.get(position);
		if(building!=null){
			TextViewWriterUtil.writeValue(viewHolder.txtName, building.getMemberName());
			TextViewWriterUtil.writeValue(viewHolder.txtContent, building.getContent());
			TextViewWriterUtil.writeValue(viewHolder.txtTime, building.getCommentTime());
			TextViewWriterUtil.writeValue(viewHolder.txtNum, building.getCommentCount());
			TextViewWriterUtil.writeValue(viewHolder.txtPlan, ValidatorUtil.isValidString(building.getPlanTime())?building.getPlanTime()+"看房客户":null);
//			ImageLoader.getInstance().displayImage(building.getHeadPortrait(), viewHolder.imgHead);
			ImageLoaderUtil.getInstance().bindHeadImg(viewHolder.imgHead, building.getHeadPortrait());
			if(building.getCommentImgs()!=null&&building.getCommentImgs().size()>0){
				viewHolder.lltImgs.setVisibility(View.VISIBLE);
				if(params == null){
					params = (LayoutParams) viewHolder.lltImgs.getLayoutParams();
					params.height = imgHeight;
					params.width = lltWidth;
				}
				viewHolder.lltImgs.setLayoutParams(params);
//				final ImageView img1 = viewHolder.img1;
//				final LinearLayout lltImgs = viewHolder.lltImgs;
//				viewHolder.img1.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//					
//					@Override
//					public void onGlobalLayout() {
//						if(img1.getWidth()>0){
//							lltImgs.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//							LinearLayout.LayoutParams params = (LayoutParams) lltImgs.getLayoutParams();
//							params.height = (int) (img1.getWidth()*Const.SCALE);
//							lltImgs.setLayoutParams(params);
//						}
//					}
//				});
			}else{
				viewHolder.lltImgs.setVisibility(View.GONE);
			}
			try {
				ImageLoaderUtil.getInstance().bindImgCenterCrop(viewHolder.img1, building.getCommentImgs().get(0).getCommentImgSmallPath());
				viewHolder.img1.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				viewHolder.img1.setVisibility(View.INVISIBLE);
			}
			try {
				ImageLoaderUtil.getInstance().bindImgCenterCrop(viewHolder.img2, building.getCommentImgs().get(1).getCommentImgSmallPath());
				viewHolder.img2.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				viewHolder.img2.setVisibility(View.INVISIBLE);
			}
			try {
				ImageLoaderUtil.getInstance().bindImgCenterCrop(viewHolder.img3, building.getCommentImgs().get(2).getCommentImgSmallPath());
				viewHolder.img3.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				viewHolder.img3.setVisibility(View.INVISIBLE);
			}
		}
		
		return view;
	}

	class ViewHolder {
		CircleImageViewWithBorder imgHead;
		TextView txtName;
		TextView txtPlan;
		TextView txtContent;
		ImageView img1;
		ImageView img2;
		ImageView img3;
		TextView txtTime;
		TextView txtNum;
		LinearLayout lltImgs;
	}
}
