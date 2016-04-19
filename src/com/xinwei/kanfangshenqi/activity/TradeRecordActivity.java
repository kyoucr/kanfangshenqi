package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.fragment.IncomeFragment;
import com.xinwei.kanfangshenqi.fragment.PickCashFragment;
import com.xinwei.kanfangshenqi.view.TransLateView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
/**
 ********************
 * 交易记录
 * @author cn
 ********************
 */
public class TradeRecordActivity extends BaseFragmentActivity {
	@ViewInject(R.id.transLateView)
	private TransLateView transLateView;
	@ViewInject(R.id.radioGroup)
	private RadioGroup radioGroup;
	@ViewInject(R.id.radioBtnCash)
	private RadioButton radioBtnCash;
	@ViewInject(R.id.radioBtnIncome)
	private RadioButton radioBtnIncome;
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	private PickCashFragment pickCashFragment;
	private IncomeFragment incomeFragment;
	private static final int CASH_PAGE = 0;
	private static final int INCOME_PAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_trade_record);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.trade_record);
		setLeftTitle(R.string.cash_redpacket);
		isShowTitleCutline(false);
		viewPager.setAdapter(new TradeRecordFragmentAdp(
				getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case CASH_PAGE:
					radioBtnCash.setChecked(true);
					break;
				case INCOME_PAGE:
					radioBtnIncome.setChecked(true);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				transLateView.setXx((float) arg0 * transLateView.getW()
						+ transLateView.getW() * arg1);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		transLateView.setInitX((float) radioBtnCash.getWidth());
		radioBtnCash.setChecked(true);
	}
	@Event(value=R.id.radioGroup,type=OnCheckedChangeListener.class)
	private void onCheckChangedEvent(RadioGroup group, int checkedId){
		switch (checkedId) {
		case R.id.radioBtnCash:
			viewPager.setCurrentItem(CASH_PAGE);
			break;
		case R.id.radioBtnIncome:
			viewPager.setCurrentItem(INCOME_PAGE);
			break;
		}
	}
	@Override
	public void onReloadData() {

	}

	@Override
	public String getRequestTag() {
		return TradeRecordActivity.class.getSimpleName();
	}

	class TradeRecordFragmentAdp extends FragmentPagerAdapter {

		public TradeRecordFragmentAdp(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			BaseFragment fragment = null;
			switch (arg0) {
			case CASH_PAGE:
				if (pickCashFragment == null)
					pickCashFragment = new PickCashFragment();
				fragment = pickCashFragment;
				break;
			case INCOME_PAGE:
				if (incomeFragment == null)
					incomeFragment = new IncomeFragment();
				fragment = incomeFragment;
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}

}
