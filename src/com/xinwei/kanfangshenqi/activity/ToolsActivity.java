package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.ToolsAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.util.Utils;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
/**
 ********************
 * 常用工具
 * @author cn
 ********************
 */
public class ToolsActivity extends BaseActivity{
	@ViewInject(R.id.gridView)
	private GridView gridView;
	private ToolsAdp toolsAdp;
	private List<String> toolsList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_tools);
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.title_tools);
		setLeftTitle(R.string.tab_bar_ic_faxian);
		toolsList = new ArrayList<String>();
		toolsList.add(getString(R.string.txt_loan_calculator));
		toolsList.add(getString(R.string.txt_buy_house_ability));
		toolsList.add(getString(R.string.txt_accumulation_fund_calculator));
		toolsList.add(getString(R.string.txt_advance_repayment_calculator));
		toolsList.add(getString(R.string.txt_taxation_calculator));
		toolsList.add(getString(R.string.txt_accumulation_fund_loan_interest));
		toolsAdp = new ToolsAdp(activity,toolsList);
		gridView.setAdapter(toolsAdp);
	}
	@Event(value=R.id.gridView,type=OnItemClickListener.class)
	private void toItemEvent(AdapterView<?> arg0, View arg1, int position,
					long arg3){
		String txtCtrl = toolsList.get(position);
		Bundle data = new Bundle();
		data.putString(Const.WEB_LEFT_TITLE_KEY, getString(R.string.title_tools));
		if(getString(R.string.txt_loan_calculator).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_loan_calculator));
			data.putString(Const.WEB_URL_KEY, Const.URL_LOAN_CALCULATOR);
		}else if(getString(R.string.txt_buy_house_ability).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_buy_house_ability));
			data.putString(Const.WEB_URL_KEY, Const.URL_BUY_HOUSE_ABILITY);
		}else if(getString(R.string.txt_accumulation_fund_calculator).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_accumulation_fund_calculator));
			data.putString(Const.WEB_URL_KEY, Const.URL_ACCUMULATION_FUND_CALCULATOR);
		}else if(getString(R.string.txt_advance_repayment_calculator).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_advance_repayment_calculator));
			data.putString(Const.WEB_URL_KEY, Const.URL_ADVANCE_REPAYMENT_CALCULATOR);
		}else if(getString(R.string.txt_taxation_calculator).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_taxation_calculator));
			data.putString(Const.WEB_URL_KEY, Const.URL_TAXATION_CALCULATOR);
		}else if(getString(R.string.txt_accumulation_fund_loan_interest).equals(txtCtrl)){
			data.putString(Const.WEB_TITLE_KEY, getString(R.string.txt_accumulation_fund_loan_interest));
			data.putString(Const.WEB_URL_KEY, Const.URL_ACCUMULATION_FUND_LOAN_INTEREST);
		}
		Utils.moveTo(activity, WebActivity.class, false, data);
	}
	@Override
	public void onReloadData() {
		
	}

	@Override
	public String getRequestTag() {
		return null;
	}

}