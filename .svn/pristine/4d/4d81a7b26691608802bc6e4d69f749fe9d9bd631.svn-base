package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.ShareQQ;
import com.xinwei.kanfangshenqi.common.ShareWeiXin;
import com.xinwei.kanfangshenqi.common.ShareXinLang;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;

public class ShareAdp extends BaseAbsAdp{
	private Activity activity;
	private List<String> toolsList;
	private PopupWindow pop;
	public ShareAdp(Activity activity,List<String> toolsList,PopupWindow pop) {
		this.activity = activity;
		this.toolsList = toolsList;
		this.pop = pop;
	}
	@Override
	public List<?> onGetListData() {
		return toolsList;
	}
	
	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if(view == null){
			view = LayoutInflater.from(activity).inflate(R.layout.adp_share, null);
			viewHolder = new ViewHolder();
			viewHolder.imgCtrl = (ImageView) view.findViewById(R.id.imgCtrl);
			viewHolder.txtCtrl = (TextView) view.findViewById(R.id.txtCtrl);
			viewHolder.lltItem = (LinearLayout) view.findViewById(R.id.lltItem);
			RelativeLayout.LayoutParams params = (LayoutParams) viewHolder.lltItem.getLayoutParams();
			params.height = Utils.getDeviceWidth((Activity)activity)/3;
			viewHolder.lltItem.setLayoutParams(params);
			view.setTag(viewHolder);
		}else
			viewHolder = (ViewHolder) view.getTag();
		final String txtCtrl = toolsList.get(position);
		TextViewWriterUtil.writeValue(viewHolder.txtCtrl, txtCtrl);
		//分享
		if(activity.getString(R.string.txt_wx_friend).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.wechat_icon);
		}else if(activity.getString(R.string.txt_circle_friend).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.pengyouquan_icon);
		}else if(activity.getString(R.string.txt_sms).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.duanxin_icon);
		}else if(activity.getString(R.string.txt_qq_friend).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.qq_icon);
		}else if(activity.getString(R.string.txt_qq_space).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.qqkongjian_icon);
		}else if(activity.getString(R.string.txt_xin_lang).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.xinlangweibo_icon);
		}
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(pop!=null)
					pop.dismiss();
				if (activity.getString(R.string.txt_wx_friend).equals(txtCtrl)) {
					try {
						ShareWeiXin.getInstance(activity).shareFriend();
					} catch (Exception e) {
						ToastUtil.show(activity, "分享失败，请检查您的微信客户端！");
					}
				} else if (activity.getString(R.string.txt_circle_friend).equals(
						txtCtrl)) {
					try {
						ShareWeiXin.getInstance(activity).shareCircle();
					} catch (Exception e) {
						ToastUtil.show(activity, "分享失败，请检查您的微信客户端！");
					}
				} else if (activity.getString(R.string.txt_sms).equals(txtCtrl)) {
					Utils.shareSMS(activity);
				} else if (activity.getString(R.string.txt_qq_friend)
						.equals(txtCtrl)) {
					try {
						ShareQQ.getInstance(activity).shareQQ();
					} catch (Exception e) {
						ToastUtil.show(activity, "分享失败，请检查您的QQ客户端！");
					}
				} else if (activity.getString(R.string.txt_qq_space).equals(txtCtrl)) {
					try {
						ShareQQ.getInstance(activity).shareQZone();
					} catch (Exception e) {
						ToastUtil.show(activity, "分享失败，请检查您的QQ客户端！");
					}
				} else if (activity.getString(R.string.txt_xin_lang).equals(txtCtrl)) {
					try {
						ShareXinLang.getInstance(activity).shareWB();
					} catch (Exception e) {
						ToastUtil.show(activity, "分享失败，请检查您的新浪客户端！");
					}
				}
			}
		});
		return view;
	}
	class ViewHolder {
		ImageView imgCtrl;
		TextView txtCtrl;
		LinearLayout lltItem;
	}
}
