package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.DensityUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;

public class ToolsAdp extends BaseAbsAdp{
	private Context context;
	private List<String> toolsList;
	public ToolsAdp(Context context,List<String> toolsList) {
		this.context = context;
		this.toolsList = toolsList;
	}
	@Override
	public List<?> onGetListData() {
		return toolsList;
	}
	
	@Override
	public View onGetView(int position, View view) {
		ViewHolder viewHolder;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.adp_tools, null);
			viewHolder = new ViewHolder();
			viewHolder.imgCtrl = (ImageView) view.findViewById(R.id.imgCtrl);
			viewHolder.txtCtrl = (TextView) view.findViewById(R.id.txtCtrl);
			viewHolder.lltItem = (LinearLayout) view.findViewById(R.id.lltItem);
			RelativeLayout.LayoutParams params = (LayoutParams) viewHolder.lltItem.getLayoutParams();
			params.height = Utils.getDeviceWidth((Activity)context)/3;
			viewHolder.lltItem.setLayoutParams(params);
			view.setTag(viewHolder);
		}else
			viewHolder = (ViewHolder) view.getTag();
		String txtCtrl = toolsList.get(position);
		TextViewWriterUtil.writeValue(viewHolder.txtCtrl, txtCtrl);
		//常用工具
		if(context.getString(R.string.txt_loan_calculator).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.loancalc_icon);
		}else if(context.getString(R.string.txt_buy_house_ability).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.purchasing_ability_evaluation_icon);
		}else if(context.getString(R.string.txt_accumulation_fund_calculator).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.provident_fund_icon);
		}else if(context.getString(R.string.txt_advance_repayment_calculator).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.prepayment_icon);
		}else if(context.getString(R.string.txt_taxation_calculator).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.taxes_icon);
		}else if(context.getString(R.string.txt_accumulation_fund_loan_interest).equals(txtCtrl)){
			viewHolder.imgCtrl.setBackgroundResource(R.drawable.interest_rate_icon);
		}
		return view;
	}
	class ViewHolder {
		ImageView imgCtrl;
		TextView txtCtrl;
		LinearLayout lltItem;
	}
}
