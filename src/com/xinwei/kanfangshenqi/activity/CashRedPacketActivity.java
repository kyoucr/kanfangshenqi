package com.xinwei.kanfangshenqi.activity;

import java.util.List;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.IncomeAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.CashRedPacket;
import com.xinwei.kanfangshenqi.model.Income;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.ListViewInScroll;
import com.xinwei.kanfangshenqi.view.RiseNumberTextView;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *******************
 * 现金红包
 * 
 * @author cn
 *
 *******************
 */
public class CashRedPacketActivity extends BaseActivity implements OnRefreshListener2 {
	@ViewInject(R.id.rltTop)
	private RelativeLayout rltTop;
	@ViewInject(R.id.rltBottom)
	private RelativeLayout rltBottom;
	@ViewInject(R.id.lViIncome)
	private ListViewInScroll lViIncome;
	@ViewInject(R.id.scrollRefresh)
	private PullToRefreshScrollView scrollRefresh;
	private int beginPage = 1;
	private List<Income> listIncomeData;
	@ViewInject(R.id.txtCash)
	private RiseNumberTextView txtCash;
	// @ViewInject(R.id.txtCashNone)
	// private TextView txtCashNone;
	@ViewInject(R.id.rltEmpty)
	private RelativeLayout rltEmpty;
	@ViewInject(R.id.txtLeftCustom)
	private TextView txtLeftCustom;
	@ViewInject(R.id.txtWait)
	private RiseNumberTextView txtWait;
	@ViewInject(R.id.txtAccumulative)
	private RiseNumberTextView txtAccumulative;
	private IncomeAdp incomeAdp;
	private CashRedPacket cash;
	public static OnCashPickedListener OnCashPickedListener;
	private OnCashPickedListener onCashPickedListener = new OnCashPickedListener() {
		@Override
		public void onCashPicked() {
			onReloadData();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_cash_red_packet);
		addCustomTitle(R.layout.title_cash_red_packet);
	}

	@Override
	public void onChildViewLoaded() {
		LinearLayout.LayoutParams paramsTop = (android.widget.LinearLayout.LayoutParams) rltTop.getLayoutParams();
		paramsTop.height = (int) ((Utils.getDeviceHeight(activity) - Utils.getStatusBarHeight()
				- getResources().getDimension(R.dimen.title_height) * 2 - Utils.dip2px(activity, 0.5f)) / 2);
		RelativeLayout.LayoutParams paramsEmpty = (android.widget.RelativeLayout.LayoutParams) rltEmpty.getLayoutParams();
		paramsEmpty.height = (int) ((Utils.getDeviceHeight(activity) - Utils.getStatusBarHeight()
				- getResources().getDimension(R.dimen.title_height) * 2 - Utils.dip2px(activity, 0.5f)) / 2);
//		LinearLayout.LayoutParams paramsBottom = (android.widget.LinearLayout.LayoutParams) rltBottom.getLayoutParams();
//		paramsBottom.height = (int) ((Utils.getDeviceHeight(activity) - Utils.getStatusBarHeight()
//				- getResources().getDimension(R.dimen.title_height) * 2 - Utils.dip2px(activity, 0.5f)) / 2);
		String leftTitle = getIntent().getStringExtra(Const.WEB_LEFT_TITLE_KEY);
		if (ValidatorUtil.isValidString(leftTitle)) {
			TextViewWriterUtil.writeValue(txtLeftCustom, leftTitle);
		}
		scrollRefresh.setOnRefreshListener(this);
		onReloadData();
	}

	@Override
	public void onReloadData() {
		showBar();
		setBarCancelable(false);
		HttpRequest.get(this, Const.URL_ENVELOPERECEIVE + String.valueOf(beginPage), getRequestTag(), null,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						cash = new Gson().fromJson(responseResult, CashRedPacket.class);
//						cash.getIncomeList().getDataList().addAll(cash.getIncomeList().getDataList());
//						cash.getIncomeList().getDataList().addAll(cash.getIncomeList().getDataList());
						Utils.judgePullRefreshStatus(scrollRefresh, beginPage, cash.getIncomeList().getPageCount());
						listIncomeData = cash.getIncomeList().getDataList();
						if (!ValidatorUtil.isValidList(listIncomeData) || Integer.parseInt(cash.getCashAmount()) == 0) {
							rltEmpty.setVisibility(View.VISIBLE);
							lViIncome.setVisibility(View.GONE);
							showEmptyMoney();
						} else {
							rltEmpty.setVisibility(View.GONE);
							lViIncome.setVisibility(View.VISIBLE);
							showIncomeMoney();
							incomeAdp = new IncomeAdp(activity, listIncomeData);
							lViIncome.setAdapter(incomeAdp);
						}
						txtWait.withNumber(Integer.parseInt(cash.getNotActivation()));
						txtWait.start();
						txtAccumulative.withNumber(Integer.parseInt(cash.getGain()));
						txtAccumulative.start();
						isShowContent(true);
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						isShowError(true);
					}

					@Override
					public void onError(String url, String responseResult) {
						isShowError(true);
					}
				}, true);
	}

	@Override
	public String getRequestTag() {
		return CashRedPacketActivity.class.getSimpleName();
	}

	@Event({ R.id.rltSubTitle, R.id.btnRecord, R.id.btnPick })
	private void finishEvent(View v) {
		switch (v.getId()) {
		case R.id.rltSubTitle:
			finish();
			break;
		case R.id.btnRecord:
			Utils.moveTo(activity, TradeRecordActivity.class, false, null);
			break;
		case R.id.btnPick:
			if (Integer.parseInt(cash.getCashAmount()) <= 0) {
				ToastUtil.show(activity, getString(R.string.hint_red_packet_empty_title));
				return;
			}
			Bundle data = new Bundle();
			data.putSerializable(CashRedPacket.class.getSimpleName(), cash);
			Utils.moveTo(activity, PickCashActivity.class, false, data);
			OnCashPickedListener = onCashPickedListener;
			break;
		}
	}

	private void showEmptyMoney() {
		txtCash.setText(getString(R.string.hint_red_packet_empty_title));
		txtCash.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.txt_cash_none_size));
	}

	private void showIncomeMoney() {
		txtCash.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.txt_cash_size));
		txtCash.withNumber(Integer.parseInt(cash.getCashAmount()));
		txtCash.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		OnCashPickedListener = null;
	}

	public interface OnCashPickedListener {
		public void onCashPicked();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView, int mBeginPage) {
		HttpRequest.get(this, Const.URL_ENVELOPERECEIVE + String.valueOf(beginPage), getRequestTag(), null,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						cash = new Gson().fromJson(responseResult, CashRedPacket.class);
						Utils.judgePullRefreshStatus(scrollRefresh, beginPage, cash.getIncomeList().getPageCount());
						listIncomeData = cash.getIncomeList().getDataList();
						if (!ValidatorUtil.isValidList(listIncomeData) || Integer.parseInt(cash.getCashAmount()) == 0) {
							rltEmpty.setVisibility(View.VISIBLE);
							lViIncome.setVisibility(View.GONE);
							showEmptyMoney();
						} else {
							rltEmpty.setVisibility(View.GONE);
							lViIncome.setVisibility(View.VISIBLE);
							showIncomeMoney();
							incomeAdp = new IncomeAdp(activity, listIncomeData);
							lViIncome.setAdapter(incomeAdp);
						}
						txtWait.withNumber(Integer.parseInt(cash.getNotActivation()));
						txtWait.start();
						txtAccumulative.withNumber(Integer.parseInt(cash.getGain()));
						txtAccumulative.start();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						scrollRefresh.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						scrollRefresh.onRefreshComplete(false);
					}
				}, true);
	}

	@Override
	public void onPullUpToLoad(PullToRefreshBase refreshView, final int mNextPage) {
		HttpRequest.get(this, Const.URL_ENVELOPERECEIVE + String.valueOf(mNextPage), getRequestTag(), null,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						cash = new Gson().fromJson(responseResult, CashRedPacket.class);
						Utils.judgePullRefreshStatus(scrollRefresh, mNextPage, cash.getIncomeList().getPageCount());
						listIncomeData.addAll(cash.getIncomeList().getDataList());
						incomeAdp.notifyDataSetChanged();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						scrollRefresh.onRefreshComplete(false);
					}

					@Override
					public void onError(String url, String responseResult) {
						scrollRefresh.onRefreshComplete(false);
					}
				}, true);
	}
}