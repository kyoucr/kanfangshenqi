package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.Desc;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SurroundingAdp extends BaseAbsAdpWithViewHolder {
	private List<Desc> dataList;
	private boolean isShowAll;
	@SuppressWarnings("unchecked")
	public SurroundingAdp(Context context, List<?> list, int layoutId,boolean isShowAll) {
		super(context, list, layoutId);
		this.dataList = (List<Desc>) list;
		this.isShowAll = isShowAll;
	}

	public boolean isShowAll() {
		return isShowAll;
	}

	public void setShowAll(boolean isShowAll) {
		this.isShowAll = isShowAll;
	}

//	@Override
//	public int getCount() {
//		if (isShowAll && dataList != null){
//			return dataList.size();
//		}else{
//			return dataList == null ? 0 : dataList.size() > 5 ? 5 : dataList.size();
//		}
//	}
	@Override
	public void onGetView(int position, ViewHolder viewHolder) {
		Desc desc = dataList.get(position);
		if (desc != null) {
			TextView txtKey = viewHolder.getView(R.id.txtKey);
			TextView txtValue = viewHolder.getView(R.id.txtValue);
			if(ValidatorUtil.isValidString(desc.getKey())){
				txtKey.setVisibility(View.VISIBLE);
				TextViewWriterUtil.writeValue(txtKey, desc.getKey()+"：");
			}else{
				txtKey.setVisibility(View.GONE);
			}
			TextViewWriterUtil.writeValue(txtValue, desc.getValue());
		}
	}

}
