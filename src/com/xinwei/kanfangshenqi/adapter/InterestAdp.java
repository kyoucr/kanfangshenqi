package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import org.xutils.common.util.DensityUtil;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.housecircle.InterestFragment;
import com.xinwei.kanfangshenqi.model.Interest;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.AlertWidget;
import com.xinwei.kanfangshenqi.view.CornerRadiusButton;
import com.xinwei.kanfangshenqi.view.CornerRadiusTextView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 ********************
 * 关注列表适配器
 * @author cn
 ********************
 */
public class InterestAdp extends BaseAbsAdp {
	private Context context;
	private List<Interest> interestList;
	public InterestAdp(Context context, List<Interest> interestList) {
		this.context = context;
		this.interestList = interestList;
	}

	@Override
	public List<?> onGetListData() {
		return interestList;
	}

	@Override
	public View onGetView(final int position, View view) {
		ViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_interest,
					null);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) view.findViewById(R.id.img);
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtDiscuss = (TextView) view
					.findViewById(R.id.txtDiscuss);
			viewHolder.btnCancelInterest = (CornerRadiusTextView) view
					.findViewById(R.id.btnCancelInterest);
			viewHolder.rltLayout = (RelativeLayout) view
					.findViewById(R.id.rltLayout);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		final Interest interest = interestList.get(position);
		if (interest != null) {
			viewHolder.rltLayout.setVisibility(View.VISIBLE);
			TextViewWriterUtil.writeValue(viewHolder.txtName,
					interest.getBuildingName());
			TextViewWriterUtil.writeValue(
					viewHolder.txtDiscuss,
					context.getString(R.string.txt_discuss_amount,
							interest.getCommentCount()));
			ImageLoaderUtil.getInstance().bindImgFixXY(viewHolder.img, interest.getSmallBanner());
			try {
				viewHolder.btnCancelInterest
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								
								final AlertWidget alert = new AlertWidget(context);
								alert.showCustom(null, context.getString(R.string.alert_cancel_interest_content), null, null, new OnClickListener() {
									@Override
									public void onClick(View v) {
										alert.close();
										cancelInterest(interest.getFollowId(),position);								
									}
								});
								alert.show();
							}
						});
			} catch (Exception e) {
			}
		}else{
			viewHolder.rltLayout.setVisibility(View.INVISIBLE);
		}
		return view;
	}

	private void cancelInterest(String id,final int position) {
		if(!ValidatorUtil.isValidString(id)){
			return;
		}
		showBar(context);
		HttpRequest.post(context, Const.URL_CANCEL_INTEREST+id,
				InterestFragment.class.getSimpleName(), null,Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						ToastUtil.show(context, context.getString(R.string.hint_cancel_interest_success));
						((SeeHouseCircleActivity) context)
						.reduceInterestNum();
						interestList.remove(position);
						notifyDataSetChanged();
						closeBar();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						closeBar();
					}
				});
	}

	class ViewHolder {
		TextView txtName;
		TextView txtDiscuss;
		ImageView img;
		CornerRadiusTextView btnCancelInterest;
		RelativeLayout rltLayout;
	}
}