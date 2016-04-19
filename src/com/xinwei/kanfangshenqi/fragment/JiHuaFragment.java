package com.xinwei.kanfangshenqi.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.LocalDate;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.BaseFragment2;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.LouPanActivity;
import com.xinwei.kanfangshenqi.activity.MainActivity;
import com.xinwei.kanfangshenqi.adapter.PlansAdapter;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.calendar.CollapseCalendarView;
import com.xinwei.kanfangshenqi.calendar.CollapseCalendarView.OnDateSelect;
import com.xinwei.kanfangshenqi.calendar.CollapseCalendarView.OnTitleClickListener;
import com.xinwei.kanfangshenqi.calendar.manager.CalendarManager;
import com.xinwei.kanfangshenqi.calendar.manager.CalendarManager.OnMonthChangeListener;
import com.xinwei.kanfangshenqi.model.PlansInfo;
import com.xinwei.kanfangshenqi.model.PlansModel;
import com.xinwei.kanfangshenqi.model.UnPlan;
import com.xinwei.kanfangshenqi.request.PlansRequest;
import com.xinwei.kanfangshenqi.response.PlansResponse;
import com.xinwei.kanfangshenqi.util.DensityUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.WebUtils;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author jiangzl
 * 
 */
public class JiHuaFragment extends BaseFragment2 implements Response.ErrorListener {
	@ViewInject(R.id.contentPager)
	private View mContentPager;
	@ViewInject(R.id.handle_gridview)
	private ListView handle_gridview;
	private LayoutInflater inflater;
	private ArrayList<PlansInfo> PlansInfos = new ArrayList<PlansInfo>();
	private ArrayList<PlansInfo> handle_PlansInfos = new ArrayList<PlansInfo>();
	private ArrayList<PlansInfo> content_PlansInfos = new ArrayList<PlansInfo>();
	@ViewInject(R.id.tv_data)
	private TextView tv_data;
	@ViewInject(R.id.iv_jihua_Right)
	private ImageView iv_jihua_Right;
	public static List<String> datalist = new ArrayList<String>();
	public static boolean isNullData = true;
	@ViewInject(R.id.plans_button)
	private TextView plans_button;
	@ViewInject(R.id.null_show_iv)
	private ImageView null_show_iv;
	private PopupWindow popuWindow1;
	private View contentView1;
	@ViewInject(R.id.handle)
	private LinearLayout handleLinearLayout;
	@ViewInject(R.id.toastText)
	private TextView tosttext;

	private CollapseCalendarView calendarView;
	private CalendarManager mManager;
	private boolean show = false;
	@ViewInject(R.id.rrreee)
	private RelativeLayout rrreee;
	@ViewInject(R.id.iv_jihua_rili_left)
	private ImageView rili_left;
	@ViewInject(R.id.iv_jihua_rili_right)
	private ImageView rili_right;
	@ViewInject(R.id.iv_jihua_rili_content)
	private TextView rili_content;
	@ViewInject(R.id.lltCalendar)
	private LinearLayout lltCalendar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		addChildView(R.layout.fragment_jihua);
	}

	/**
	 *******************
	 * 修改右上角图标
	 * 
	 * @param unPlan未设置看房时间的计划数量
	 *******************
	 */
	@Subscribe
	public void onUnPlanEvent(UnPlan unPlan) {
		if (ValidatorUtil.isValidString(unPlan.getUnPlanNum())) {
			try {
				if (Integer.parseInt(unPlan.getUnPlanNum()) > 0)
					iv_jihua_Right.setBackgroundResource(R.drawable.nav_list_button_unread);
				else
					iv_jihua_Right.setBackgroundResource(R.drawable.nav_list_button);
			} catch (Exception e) {
				iv_jihua_Right.setBackgroundResource(R.drawable.nav_list_button);
			}
		} else
			iv_jihua_Right.setBackgroundResource(R.drawable.nav_list_button);
	}

	@Event(value = { R.id.iv_jihua_Right, R.id.iv_jihua_rili_left, R.id.iv_jihua_rili_right, R.id.rrreee,R.id.plans_button })
	private void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.iv_jihua_Right:
			Utils.moveTo(getActivity(), LouPanActivity.class, false, null);
			break;
		case R.id.iv_jihua_rili_left:
			calendarView.prev();
			break;
		case R.id.iv_jihua_rili_right:
			calendarView.next();
			break;
		case R.id.rrreee:
			mManager.toggleView();
			calendarView.populateLayout();
			break;
		case R.id.plans_button:
			if (Utils.getHeaderParamsOnly() == null)
				getAlert().showLogin();
			else
				((MainActivity) activity).showFindHouseFragment();
			break;
		default:
			break;
		}

	}

	public void setShowDateForDayViewText(int day) {
		tv_data.setText(day + "日");
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		closeBar();
	}

	@Override
	public void onChildViewLoaded() {
		judgeEmptyBg();
		this.inflater = LayoutInflater.from(activity);
		onUnPlanEvent(new UnPlan());
		int[] location = new int[2];
		iv_jihua_Right.getLocationOnScreen(location);
	}

	private void calendarViewInit() {
		calendarView = (CollapseCalendarView) parentView.findViewById(R.id.calendar);
		mManager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, LocalDate.now().withYear(100),
				LocalDate.now().plusYears(100));
		// 月份切换监听器
		mManager.setMonthChangeListener(new OnMonthChangeListener() {

			@Override
			public void monthChange(String month, LocalDate mSelected) {
				rili_content.setText(month);
			}

		});
		/**
		 * 日期选中监听器
		 */
		calendarView.setDateSelectListener(new OnDateSelect() {

			@Override
			public void onDateSelected(LocalDate date) {
				setShowDateForDayViewText(date.getDayOfMonth());
				isData(date);
			}
		});
		calendarView.setTitleClickListener(new OnTitleClickListener() {

			@Override
			public void onTitleClick() {
				calendarView.changeDate(LocalDate.now().toString());
			}
		});
		// 是否显示阴历
		calendarView.showChinaDay(show);
		// 设置数据显示
		calendarView.setArrayData(datalist);
		// 初始化日历管理器
		calendarView.hideHeader();
		calendarView.init(mManager);
	}

	@Override
	public void onReloadData() {
		JiHuaFragment.class.getSimpleName();
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}
	public void initCalendar(){
		if(calendarView == null){
			calendarViewInit();
		}
		if(lltCalendar!=null)
			lltCalendar.setVisibility(View.VISIBLE);
	}
	@Override
	public String getRequestTag() {
		return JiHuaFragment.class.getSimpleName();
	}

	private void isData(final LocalDate data) {
		PlansRequest request = new PlansRequest(new Listener<PlansResponse>() {
			@Override
			public void onResponse(PlansResponse response) {
				closeBar();
				if (response != null && response.getStatus().equals("1")) {// success
					if (0 < handle_PlansInfos.size()) {
						handle_PlansInfos.clear();
					}
					if (0 < content_PlansInfos.size()) {
						content_PlansInfos.clear();
					}
					initCalendar();
					if (response.getDataList() != null) {
						for (int i = 0; i < response.getDataList().size(); i++) {
							if (data.toString().equals(response.getDataList().get(i).getAppointmentTime())) {
								tosttext.setVisibility(View.INVISIBLE);
								PlansInfos = response.getDataList().get(i).getData();
								for (int k = 0; k < PlansInfos.size(); k++) {
									// if (k < 2) {
									handle_PlansInfos.add(PlansInfos.get(k));
									// } else {
									// content_PlansInfos.add(PlansInfos.get(k));
									// }
								}
								break;
							} else {
								tosttext.setVisibility(View.VISIBLE);
							}
						}
						handle_gridview.setAdapter(new PlansAdapter(handle_PlansInfos, inflater, getActivity()));
						// content_gridview.setAdapter(new PlansAdapter(
						// content_PlansInfos, inflater, getActivity()));
						// handleAdapter.notifyDataSetChanged();
					} else {
						handle_gridview.setAdapter(new PlansAdapter(handle_PlansInfos, inflater, getActivity()));
						// content_gridview.setAdapter(new PlansAdapter(
						// content_PlansInfos, inflater, getActivity()));
						// handleAdapter.notifyDataSetChanged();
						// handleAdapter = new PlansAdapter(handle_PlansInfos,
						// inflater, getActivity());
						// handle_gridview.setAdapter(handleAdapter);
						tosttext.setVisibility(View.VISIBLE);
					}
				}
			}

		}, this);
		request.setToken(PreferenceUtils.getInstance(activity).getSettingUserToken());
		request.setAppagent(PreferenceUtils.getInstance(activity).getSettingUserAppAgent());
		request.setOsver(PreferenceUtils.getInstance(activity).getSettingUserOSVer());
		request.setTransid(PreferenceUtils.getInstance(activity).getSettingUserTransId());
		WebUtils.doPost(request);
		showBar();
	}

	@Override
	public void onStart() {
		super.onStart();
		if (calendarView != null) {
			calendarView.setArrayData(datalist);
			// 初始化日历管理器
			calendarView.init(mManager);
		}
	}

	public void judgeEmptyData() {
		String token = PreferenceUtils.getInstance().getSettingUserToken();
		if (null_show_iv != null && plans_button != null) {
			if (!StringUtils.isEmpty(token) && !isNullData) {
				null_show_iv.setVisibility(View.GONE);
				plans_button.setVisibility(View.GONE);
				if ("0".equals(PreferenceUtils.getInstance().getIsFirstJihuaIsData()) && isVisible()) {
					if (MainActivity.INSTANCE != null && MainActivity.INSTANCE.currentFragment != null
							&& MainActivity.INSTANCE.currentFragment == MainActivity.INSTANCE.planFragment) {
						initPopuWindow();
						PreferenceUtils.getInstance().setIsFirstJihuaIsData("1");
					}
				}
			} else {
				lltCalendar.setVisibility(View.GONE);
				judgeEmptyBg();
				null_show_iv.setVisibility(View.VISIBLE);
				plans_button.setVisibility(View.VISIBLE);
			}
		}
	}
	private void judgeEmptyBg(){
		SimpleDateFormat ddf = new SimpleDateFormat("HH");
		int hours = Integer.parseInt(ddf.format(new Date()));
		if (hours > 17) {
			null_show_iv.setBackgroundResource(R.drawable.jihua_bg);
		} else {
			null_show_iv.setBackgroundResource(R.drawable.jihua_day_bg);
		}
	}
	public void showUnLogin() {
		if (null_show_iv != null) {
			null_show_iv.setVisibility(View.VISIBLE);
			plans_button.setVisibility(View.VISIBLE);
		}
	}

	private void initPopuWindow() {
		if (popuWindow1 == null) {
			contentView1 = inflater.inflate(R.layout.popuwindow_course_setjourney, null);
			popuWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			ImageView imgTop = (ImageView) contentView1.findViewById(R.id.imgTop);
			RelativeLayout.LayoutParams params = (LayoutParams) imgTop.getLayoutParams();
			params.height = DensityUtil.dip2px(XWApplication.getInstance(), 5.5f) + Utils.getStatusBarHeight();
			imgTop.setLayoutParams(params);
			contentView1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					popuWindow1.dismiss();
				}
			});
		}
		ColorDrawable cd = new ColorDrawable(0x000000);
		popuWindow1.setBackgroundDrawable(cd);
		// 产生背景变暗效果
		popuWindow1.setOutsideTouchable(true);
		popuWindow1.setFocusable(true);
		popuWindow1.showAtLocation(parentView, Gravity.TOP, 0, 0);
	}

	@Override
	public void onDestroy() {
		try {
			EventBus.getDefault().unregister(this);
		} catch (Exception e) {
		}
		super.onDestroy();
	}

	private void SlidingDraweInit(int cellSpace) {
		handleLinearLayout.getLayoutParams().height = mContentPager.getHeight() - cellSpace * 6;
	}
}
