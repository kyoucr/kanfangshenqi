package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.CashRedPacket;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 ********************
 * 提现
 * @author cn
 ********************
 */
public class PickCashActivity extends BaseActivity {
	@ViewInject(R.id.txtPhone)
	private TextView txtPhone;
	@ViewInject(R.id.edtTxtMoney)
	private EditText edtTxtMoney;
	@ViewInject(R.id.imgCheckZhiFuBao)
	private ImageView imgCheckZhiFuBao;
	private List<Boolean> checkList;//存放支付方式，判断哪个被选中
	private List<ImageView> iconOkList;//存放选中图标
	private final static int ZHI_FU_BAO = 0;
	private int maxPick = 0;//最大提现金额
	public static String MONEY_PICK ; 
	public static Activity INSTANCE ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		INSTANCE = this;
		addChildView(R.layout.activity_pick_cash);
	}

	@Override
	public void onChildViewLoaded() {
		initData();
		setTitleTxt(R.string.pickup_cash);
		setLeftTitle(R.string.title_cash);
		setRightTxt(R.string.txt_next_step);
		CashRedPacket cash = (CashRedPacket) getIntent().getSerializableExtra(CashRedPacket.class.getSimpleName());
		if(cash == null){
			finish();
			return;
		}
		try {
			maxPick = Integer.parseInt(cash.getCashAmount());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		edtTxtMoney.setHint(getString(R.string.txt_max_pick_cash,String.valueOf(maxPick)));
		txtPhone.setText(PreferenceUtils.getInstance(activity).getSettingUserAccount());
	}
	private void initData(){
		checkList = new ArrayList<Boolean>();
		checkList.add(ZHI_FU_BAO, false);
		iconOkList = new ArrayList<ImageView>();
		iconOkList.add(ZHI_FU_BAO,imgCheckZhiFuBao);
	}

	@Override
	public void onReloadData() {
	}

	@Event({R.id.txtRight,R.id.rltZhiFuBao})
	private void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.rltZhiFuBao:
			setCheck(ZHI_FU_BAO);
			break;
		case R.id.txtRight:
			next();
			break;
		}
	}
	private void next(){
		if(!checkList.contains(true)){
			ToastUtil.show(activity, "请选择提现方式！");
			return;
		}
		String inputMoney = edtTxtMoney.getText().toString().trim();
		if(!ValidatorUtil.isValidString(inputMoney)){
			ToastUtil.show(activity, "请输入提现金额！");
			edtTxtMoney.requestFocus();
			return;
		}
		try {
			int money = Integer.parseInt(inputMoney);
			if(money<50){
				ToastUtil.show(activity, getString(R.string.txt_min_pick_cash)+"！");
			}else if(money >maxPick ){
				ToastUtil.show(activity, "对不起，您最多可提取"+maxPick+"元！");
			}else{
				MONEY_PICK = inputMoney;
				Utils.moveTo(activity, AccountActivity.class);
			}
		} catch (NumberFormatException e) {
			ToastUtil.show(activity, "请输入整数金额！");
			e.printStackTrace();
		}
	}
	private void setCheck(int position) {
		for (int i = 0; i < checkList.size(); i++) {
			if (i == position) {
				checkList.set(i, true);
				iconOkList.get(i).setVisibility(View.VISIBLE);
			} else {
				checkList.set(i, false);
				iconOkList.get(i).setVisibility(View.INVISIBLE);
			}
		}
	}

	@Override
	public String getRequestTag() {
		return null;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		INSTANCE = null;
		MONEY_PICK = null;
	}
}