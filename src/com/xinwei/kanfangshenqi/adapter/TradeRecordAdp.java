package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.TradeRecordList.TradeRecord;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;

public class TradeRecordAdp extends BaseAbsAdp {
	private Context context;
	private List<TradeRecord> list;

	public TradeRecordAdp(Context context, List<TradeRecord> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.adp_trade_record,
					null);
			viewHolder = new ViewHolder();
			viewHolder.txtType = (TextView) view.findViewById(R.id.txtType);
			viewHolder.txtDate = (TextView) view.findViewById(R.id.txtDate);
			viewHolder.txtMoney = (TextView) view.findViewById(R.id.txtMoney);
			viewHolder.txtStatus = (TextView) view.findViewById(R.id.txtStatus);
			viewHolder.cutLineTop = view.findViewById(R.id.cutLineTop);
			viewHolder.cutLineBottom = view.findViewById(R.id.cutLineBottom);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		TradeRecord tradeRecord = list.get(position);
		if(tradeRecord!=null){
			TextViewWriterUtil.writeValue(viewHolder.txtType, tradeRecord.getType());
			TextViewWriterUtil.writeValue(viewHolder.txtDate, tradeRecord.getServiceTime());
			TextViewWriterUtil.writeValue(viewHolder.txtMoney, tradeRecord.getPrice());
			TextViewWriterUtil.writeValue(viewHolder.txtStatus, tradeRecord.getServiceStatus());
		}
		if(position == 0)
			viewHolder.cutLineTop.setVisibility(View.VISIBLE);
		else if(position == list.size()-1)
			viewHolder.cutLineBottom.setVisibility(View.VISIBLE);
		else{
			viewHolder.cutLineBottom.setVisibility(View.GONE);
			viewHolder.cutLineTop.setVisibility(View.GONE);
		}
		if(list.size()==(position+1))
			viewHolder.cutLineBottom.setVisibility(View.VISIBLE);
		return view;
	}

	class ViewHolder {
		TextView txtType;
		TextView txtDate;
		TextView txtMoney;
		TextView txtStatus;
		View cutLineTop;
		View cutLineBottom;
	}
}
