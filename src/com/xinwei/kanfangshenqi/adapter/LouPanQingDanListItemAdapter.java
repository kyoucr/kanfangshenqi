package com.xinwei.kanfangshenqi.adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.util.DensityUtil;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.LouPanActivity;
import com.xinwei.kanfangshenqi.activity.MainActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.JiHuaFragment;
import com.xinwei.kanfangshenqi.model.PlansInfo;
import com.xinwei.kanfangshenqi.model.UnPlan;
import com.xinwei.kanfangshenqi.request.PlansRequest;
import com.xinwei.kanfangshenqi.request.SetQingDanRequest;
import com.xinwei.kanfangshenqi.response.PlansResponse;
import com.xinwei.kanfangshenqi.response.SetQingDanResponse;
import com.xinwei.kanfangshenqi.util.DateUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.WebUtils;
import com.xinwei.kanfangshenqi.util.XmlUtil;
import com.xinwei.kanfangshenqi.view.AlertWidget;
import com.xinwei.kanfangshenqi.view.SlideView;
import com.xinwei.kanfangshenqi.view.SlideView.OnSlideListener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LouPanQingDanListItemAdapter extends BaseAdapter
		implements OnSlideListener, OnClickListener, Response.ErrorListener {

	private ArrayList<PlansInfo> mList;
	private LayoutInflater inflater;
	private Activity activity;
	private String day;
	private int weizhi = 0;
	private SlideView mLastSlideViewWithStatusOn;
	private OnSlideListener mListtener;
	private AlertWidget bar;
	private View view;
	public int top;
	public int left;
	public int height;
	private XmlUtil xmlUtil;
	private boolean isFirst;

	public int getWeizhi() {
		return weizhi;
	}

	public void setWeizhi(int weizhi) {
		this.weizhi = weizhi;
	}

	public LouPanQingDanListItemAdapter(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
		xmlUtil = new XmlUtil(Const.USER_INFO);
		isFirst = !Const.YES.equals(xmlUtil.get(Const.IS_FIRST_USE_INVENTORY));
	}

	public LouPanQingDanListItemAdapter(ArrayList<PlansInfo> arrayList, LayoutInflater inflater, Activity activity) {
		mList = arrayList;
		this.inflater = inflater;
		this.activity = activity;
		xmlUtil = new XmlUtil(Const.USER_INFO);
		isFirst = !Const.YES.equals(xmlUtil.get(Const.IS_FIRST_USE_INVENTORY));
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			// convertView = inflater.inflate(R.layout.item_jihua_qingdan_item,
			// null);
			convertView = inflater.inflate(R.layout.item_jihua_qingdan_slide_item, null);
			holder = new ViewHolder();
			holder.viewCutlineTop = convertView.findViewById(R.id.viewCutlineTop);
//			holder.viewCutlineBottom = convertView.findViewById(R.id.viewCutlineBottom);
			holder.loupan_name = (TextView) convertView.findViewById(R.id.loupan_name);
			holder.up_layout = (RelativeLayout) convertView.findViewById(R.id.up_layout);
			holder.loupan_jiaqian = (TextView) convertView.findViewById(R.id.loupan_jiaqian);
			holder.loupan_dizhi = (TextView) convertView.findViewById(R.id.loupan_dizhi);
			holder.qingdan_list_day = (TextView) convertView.findViewById(R.id.qingdan_list_day);
			holder.qingdan_list_rili = (ImageView) convertView.findViewById(R.id.qingdan_list_rili);
			holder.edit = convertView.findViewById(R.id.edit_rl);
			holder.del = convertView.findViewById(R.id.del_rl);
			holder.lltRed = (LinearLayout) convertView.findViewById(R.id.lltRed);
			holder.txtRed = (TextView) convertView.findViewById(R.id.txtRed);
			holder.tv_loupan_jiaqian_danwei = (TextView) convertView.findViewById(R.id.tv_loupan_jiaqian_danwei);
			holder.slideView = (SlideView) convertView.findViewById(R.id.slideView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final PlansInfo item = mList.get(position);
		if (position == 0) {
			holder.viewCutlineTop.setVisibility(View.VISIBLE);
			if (isFirst) {
				isFirst = false;
				xmlUtil.set(Const.IS_FIRST_USE_INVENTORY, Const.YES);
				holder.qingdan_list_rili.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						if (holder.qingdan_list_rili.getWidth() > 0) {
							int[] location = new int[2];
							holder.qingdan_list_rili.getLocationOnScreen(location);
							int x = location[0];
							int y = location[1];
							holder.qingdan_list_rili.getViewTreeObserver().removeGlobalOnLayoutListener(this);
							((LouPanActivity) activity).showCourse(y, x, holder.qingdan_list_rili.getHeight());

						}
					}
				});
			}
		}else{
			holder.viewCutlineTop.setVisibility(View.GONE);
		}
//		if(position == mList.size()-1){
//			holder.viewCutlineBottom.setVisibility(View.VISIBLE);
//		}else{
//			holder.viewCutlineBottom.setVisibility(View.GONE);
//		}
		holder.slideView.setOnSlideListener(this);
		item.setSlideView(holder.slideView);
		holder.slideView.shrink();
		holder.del.setTag(position);
		holder.edit.setTag(position);
		holder.del.setOnClickListener(this);
		holder.edit.setOnClickListener(this);
		holder.loupan_name.setText(item.getBuildingName());
		try {
			if (Double.parseDouble(item.getAveragePrice()) > 0) {
				holder.tv_loupan_jiaqian_danwei.setVisibility(View.VISIBLE);
				holder.loupan_jiaqian.setVisibility(View.VISIBLE);
				holder.loupan_jiaqian.setText(item.getAveragePrice());
			} else {
				holder.tv_loupan_jiaqian_danwei.setVisibility(View.GONE);
				holder.loupan_jiaqian.setVisibility(View.GONE);
			}
		} catch (NumberFormatException e1) {
			holder.tv_loupan_jiaqian_danwei.setVisibility(View.GONE);
			holder.loupan_jiaqian.setVisibility(View.GONE);
		}
		holder.loupan_dizhi.setText(item.getPosition());
		if (ValidatorUtil.isValidString(item.getScancodePrice())) {
			try {
				if (Double.parseDouble(item.getScancodePrice()) > 0) {
					holder.txtRed.setText(String.valueOf((int) Double.parseDouble(item.getScancodePrice()) + "元看房红包"));
				} else {
					holder.lltRed.setVisibility(View.GONE);
				}
			} catch (NumberFormatException e) {
				holder.lltRed.setVisibility(View.GONE);
			}
		} else {
			holder.lltRed.setVisibility(View.GONE);
		}
		if (("").equals(item.getAppointmentTime())) {
			holder.qingdan_list_rili.setBackgroundResource(R.drawable.add_calendar_icon);
			holder.qingdan_list_day.setVisibility(View.INVISIBLE);
			final Date dates = new Date();
			holder.qingdan_list_rili.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 调用日期选择器
					Calendar calendar = Calendar.getInstance();
					DatePickerDialog dpd = new DatePickerDialog(activity, new OnDateSetListener() {

						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							String day, month;
							monthOfYear = monthOfYear + 1;
							if (DateUtil.isGreater(year, monthOfYear, dayOfMonth)) {
								if (dayOfMonth < 10)
									day = "0" + dayOfMonth;
								else
									day = dayOfMonth + "";
								if (monthOfYear < 10)
									month = "0" + monthOfYear;
								else
									month = monthOfYear + "";
								// holder.qingdan_list_day.setText(day);
								// item.setAppointmentTime(year + "-" +
								// month + "-" + day);
								// holder.qingdan_list_rili.setBackgroundResource(R.drawable.choose_calendar_icon);
								// holder.qingdan_list_day.setVisibility(View.VISIBLE);
								setQingDan(item.getPlanId(), year + "-" + month + "-" + day, item);
							} else {
								Utils.showToast("目前不提供穿越业务", activity);
							}
						}
					}, dates.getYear() + 1900, dates.getMonth(), dates.getDate());
					dpd.show();
				}
			});
		} else {
			holder.qingdan_list_rili.setBackgroundResource(R.drawable.choose_calendar_icon);
			String rebackDate = item.getAppointmentTime();
			String[] subString = rebackDate.split("-");// 将rebackDate分解成几个小的字符串。分隔符是"-";
			final int year;
			final int month;
			final int day;
			year = Integer.parseInt(subString[0]);
			month = Integer.parseInt(subString[1]) - 1;
			day = Integer.parseInt(subString[2]);
			String showday;
			showday = day + "";
			if (day < 10)
				showday = "0" + day;
			// String day =
			// item.getAppointmentTime().substring(item.getAppointmentTime().lastIndexOf("-")
			// + 1);
			holder.qingdan_list_day.setVisibility(View.VISIBLE);
			holder.qingdan_list_day.setText(showday + "");
			holder.qingdan_list_rili.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 调用日期选择器
					Calendar calendar = Calendar.getInstance();
					DatePickerDialog dpd = new DatePickerDialog(activity, new OnDateSetListener() {

						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							// TODO Auto-generated method stub
							String day, month;
							monthOfYear = monthOfYear + 1;
							if (DateUtil.isGreater(year, monthOfYear, dayOfMonth)) {
								if (dayOfMonth < 10)
									day = "0" + dayOfMonth;
								else
									day = dayOfMonth + "";
								if (monthOfYear < 10)
									month = "0" + monthOfYear;
								else
									month = monthOfYear + "";
								// holder.qingdan_list_day.setText(day);
								// item.setAppointmentTime(year + "-" +
								// month + "-" + day);
								// holder.qingdan_list_rili.setBackgroundResource(R.drawable.choose_calendar_icon);
								// holder.qingdan_list_day.setVisibility(View.VISIBLE);
								setQingDan(item.getPlanId(), year + "-" + month + "-" + day, item);
							} else {
								Utils.showToast("目前不提供穿越业务", activity);
							}
						}
					}, year, month, day);
					dpd.show();
				}
			});
		}
		// if(item.getStatus().equals("0")){
		// holder.qingdan_list_rili.setBackgroundResource(R.drawable.add_calendar_icon);
		// holder.qingdan_list_day.setVisibility(View.INVISIBLE);
		// }
		return convertView;
	}

	private void setAllBgHeight(ViewHolder holder) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.up_layout.getLayoutParams();
		layoutParams.height = DensityUtil.dip2px(97);
		holder.up_layout.setLayoutParams(layoutParams);
	}

	private void setNoRedBgHeight(ViewHolder holder) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.up_layout.getLayoutParams();
		layoutParams.height = DensityUtil.dip2px(72);
		holder.up_layout.setLayoutParams(layoutParams);
	}

	public List<PlansInfo> getmList() {
		return mList;
	}

	public void setmList(ArrayList<PlansInfo> mList) {
		this.mList = mList;
	}

	class ViewHolder {
		public View viewCutlineTop;
//		public View viewCutlineBottom;
		public TextView loupan_name;
		public TextView loupan_jiaqian;
		public TextView loupan_dizhi;
		public TextView qingdan_list_day;
		public ImageView qingdan_list_rili;
		public String phone;
		public String longitude;
		public String latitude;
		public View edit;
		public View del;
		public SlideView slideView;
		public LinearLayout lltRed;
		public RelativeLayout up_layout;
		public TextView txtRed;
		public TextView tv_loupan_jiaqian_danwei;

	}

	// //当DatePickerDialog关闭时，更新日期显示
	// private void updateDate(int myyear, int monthOfYear,int dayOfMonth)
	// {
	// //在TextView上显示日期
	// getQingdan_list_day().setTextView();
	// PlansInfo item = mList.get(weizhi);
	// item.setStatus("1");
	// item.setCreateTime(myyear+"-"+monthOfYear+"-"+dayOfMonth);
	// notifyDataSetChanged();
	// }
	public void setListener(OnSlideListener listener) {
		mListtener = listener;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edit_rl:
			if (mListtener != null) {
				mListtener.onClickLeftComponent(v);
			}
			break;
		case R.id.del_rl:
			if (mListtener != null) {
				mListtener.onClickRightComponent(v);
			}
			break;
		}
	}

	@Override
	public void onSlide(View view, int status) {
		// TODO Auto-generated method stub
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}

	@Override
	public void onClickLeftComponent(View view) {
		if (mListtener != null) {
			mListtener.onClickLeftComponent(view);
		}
	}

	@Override
	public void onClickRightComponent(View view) {
	}

	private void setQingDan(String plansInd, final String date, final PlansInfo planInfo) {
		showBar();
		SetQingDanRequest request = new SetQingDanRequest(new Listener<SetQingDanResponse>() {

			@Override
			public void onResponse(SetQingDanResponse response) {
				closeBar();
				if (Const.YES.equals(response.getStatus())) {
					if (!ValidatorUtil.isValidString(planInfo.getAppointmentTime())) {
						PreferenceUtils.getInstance().minusUnappointplancount();
						EventBus.getDefault().post(new UnPlan());
					}
					planInfo.setAppointmentTime(date);
					notifyDataSetChanged();
					MainActivity.INSTANCE.onlyGetPlanData();
				} else {
					if (ValidatorUtil.isValidString(response.getMsg()))
						ToastUtil.show(activity, response.getMsg());
				}
			}
		}, this);
		request.setPlanId(plansInd);
		request.setAppointmentTime(date);
		request.setIsValid("");
		request.setToken(PreferenceUtils.getInstance(activity).getSettingUserToken());
		request.setAppagent(PreferenceUtils.getInstance(activity).getSettingUserAppAgent());
		request.setOsver(PreferenceUtils.getInstance(activity).getSettingUserOSVer());
		request.setTransid(PreferenceUtils.getInstance(activity).getSettingUserTransId());
		WebUtils.doPost(request);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		closeBar();
		ToastUtil.show(activity, activity.getString(R.string.hint_error_net));
	}

	/**
	 ******************* 
	 * 显示loading的progressbar
	 ******************* 
	 */
	public void showBar() {
		if (bar == null)
			bar = new AlertWidget(activity);
		bar.showBar();
	}

	/**
	 ******************* 
	 * 关闭bar
	 ******************* 
	 */
	public void closeBar() {
		if (bar != null)
			bar.close();
	}

}
