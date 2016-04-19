package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ViewInject;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseFragment2;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.app.XWActivityManager;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.FaxianFragment;
import com.xinwei.kanfangshenqi.fragment.JiHuaFragment;
import com.xinwei.kanfangshenqi.fragment.WoDeFragment;
import com.xinwei.kanfangshenqi.fragment.ZhaoFangFragment;
import com.xinwei.kanfangshenqi.model.GetCodeResponse;
import com.xinwei.kanfangshenqi.model.Invalidation;
import com.xinwei.kanfangshenqi.model.UnPlan;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.request.PlansRequest;
import com.xinwei.kanfangshenqi.response.LoginResponse;
import com.xinwei.kanfangshenqi.response.PlansResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.WebUtils;
import com.xinwei.kanfangshenqi.view.NestRadioGroup;
import com.xinwei.kanfangshenqi.view.NestRadioGroup.OnCheckedChangeListener;
import com.xinwei.kanfangshenqi.view.PopQRCourse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends BaseFragmentActivity implements Response.ErrorListener {
	public BaseFragment2 currentFragment;
	private FaxianFragment discoverFragment;
	public JiHuaFragment planFragment;
	public ZhaoFangFragment findHouseFragment;
	private WoDeFragment myFragment;
	@ViewInject(R.id.radioGroup)
	private NestRadioGroup radioGroup;
	@ViewInject(R.id.txtUnPlan)
	private TextView txtUnPlan;
	@ViewInject(R.id.cutLine)
	private View cutLine;
	private boolean isQuit = false;
	private Timer timer = new Timer();
	public static MainActivity INSTANCE;
	private String[] datalist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		INSTANCE = this;
		isShowTitle(false);
		EventBus.getDefault().register(this);
		addChildView(R.layout.activity_main);
		sendBroadcast(new Intent(Const.ACTION_UPLOAD_ERROR));
	}

	@Override
	protected void onStart() {
		super.onStart();
		Utils.hideKeyboard(INSTANCE);
	}

	@Subscribe
	public void onUnPlanEvent(UnPlan unPlan) {
		String num = unPlan.getUnPlanNum();
		if (ValidatorUtil.isValidString(num) && Utils.getHeaderParamsOnly() != null) {
			try {
				if (Integer.parseInt(num) > 0) {
					if (Integer.parseInt(num) > 99) {
						num = "99+";
					}
					txtUnPlan.setText(num);
					txtUnPlan.setVisibility(View.VISIBLE);
				} else {
					txtUnPlan.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				txtUnPlan.setVisibility(View.GONE);
			}
		} else
			txtUnPlan.setVisibility(View.GONE);
	}

	public void switchContent(BaseFragment2 fragment) {
		if (fragment == currentFragment || fragment == null) {
			return;
		}
		if (findHouseFragment != null && findHouseFragment.mapFragment != null
				&& findHouseFragment.mapFragment.popupwindow != null
				&& findHouseFragment.mapFragment.popupwindow.isShowing()) {
			findHouseFragment.mapFragment.popupwindow.dismiss();
		}

		if (!fragment.isAdded()) {
			getFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();
		}
		getFragmentManager().beginTransaction().show(fragment).commit();
		if (currentFragment != null)
			getFragmentManager().beginTransaction().hide(currentFragment).commit();
		currentFragment = fragment;
	}

	public void showFindHouseFragment() {
		switchContent(findHouseFragment);
		radioGroup.check(R.id.radioBtnFindHouse);
	}

	@Override
	public void onChildViewLoaded() {
		onlyGetPlanData();
		onUnPlanEvent(new UnPlan());
		findHouseFragment = new ZhaoFangFragment();
		switchContent(findHouseFragment);
		radioGroup.check(R.id.radioBtnFindHouse);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(NestRadioGroup group, int checkedId) {
				BaseFragment2 tempFragment = null;
				switch (checkedId) {
				case R.id.radioBtnFindHouse:
					if (findHouseFragment == null)
						findHouseFragment = new ZhaoFangFragment();
					tempFragment = findHouseFragment;
					break;
				case R.id.radioBtnPlan:
					if (planFragment == null)
						planFragment = new JiHuaFragment();
					tempFragment = planFragment;
					isData(planFragment, true);
					break;
				case R.id.radioBtnDiscover:
					if (discoverFragment == null)
						discoverFragment = new FaxianFragment();
					discoverFragment.requestNewMsg();
					tempFragment = discoverFragment;
					break;
				case R.id.radioBtnMy:
					if (myFragment == null)
						myFragment = new WoDeFragment();
					tempFragment = myFragment;
					break;
				}
				switchContent(tempFragment);
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}

	@Override
	public void onReloadData() {

	}

	@Override
	public String getRequestTag() {
		return null;
	}

	/**
	 * 捕捉用户按下退出按钮，控制两秒内连续按两次才能退出程序
	 */
	@Override
	public void onBackPressed() {
		/**
		 * 先询问当前显示的fragment是否需要消费返回键
		 */
		if (currentFragment != null) {
			if (currentFragment.onBackPressed()) {
				return;
			}
		}
		/**
		 * 如果fragment中无消费则进行退出逻辑
		 */
		if (isQuit == false) {
			isQuit = true;
			ToastUtil.show(this, "再按一次退出应用！");
			TimerTask task = null;
			task = new TimerTask() {
				@Override
				public void run() {
					isQuit = false;
				}
			};
			timer.schedule(task, 2000);
		} else {
			super.onBackPressed();
		}

	}

	@Subscribe
	public void onLoginResponseEvent(LoginResponse response) {
		try {
			setAlias(Utils.getHeaderParamsOnly().get(Const.PARAM_TOKEN).substring(0, 35));
		} catch (Exception e) {
		}
		isData(planFragment, true);
		if (findHouseFragment != null && findHouseFragment.mapFragment != null) {
			findHouseFragment.mapFragment.getLouPanXin(Const.URL_NAME_OF_HOUSES, ZhaoFangFragment.map, false);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		XWActivityManager.getInstance().finishAllActivity();
		INSTANCE = null;
	}

	private void isData(final JiHuaFragment planFragment, final boolean isShowBar) {
		if (Utils.getHeaderParamsOnly() == null) {
			JiHuaFragment.isNullData = true;
			if (planFragment != null)
				planFragment.judgeEmptyData();
			return;
		}
		if (isShowBar)
			showBar();
		HttpRequest.get(this, Const.URL_PLANS, getRequestTag(), null, Utils.getHeaderParamsOnly(),
				new RequestListener() {
					@Override
					public void onSuccess(String url, String responseResult) {
						if (planFragment != null)
							planFragment.initCalendar();
						if (isShowBar)
							closeBar();
						PlansResponse response = new Gson().fromJson(responseResult, PlansResponse.class);
						PreferenceUtils.getInstance(XWApplication.getInstance())
								.setSettingUserUnappointplancount(response.getUnappointPlanCount());
						EventBus.getDefault().post(new UnPlan());
						if (0 == response.getDataList().size()) {
							JiHuaFragment.isNullData = true;
						} else
							JiHuaFragment.isNullData = false;
						if (0 != response.getDataList().size()) {
							// datalist = new
							// String[response.getDataList().size()];
							// JiHuaFragment.datalist = new
							// String[response.getDataList().size()];
							if (JiHuaFragment.datalist == null)
								JiHuaFragment.datalist = new ArrayList<String>();
							else
								JiHuaFragment.datalist.clear();
							for (int i = 0; i < response.getDataList().size(); i++) {
								JiHuaFragment.datalist.add(response.getDataList().get(i).getAppointmentTime());
							}
							if (planFragment != null)
								planFragment.judgeEmptyData();
						}
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						if (isShowBar)
							closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						if (isShowBar)
							closeBar();
					}
				}, false, false);
	}

	@Subscribe
	public void onInvalidationEvent(Invalidation invalidation) {
		Utils.removeLoginInfo();
		if (JiHuaFragment.datalist != null) {
			JiHuaFragment.datalist.clear();
		}
		JiHuaFragment.isNullData = true;
		if (planFragment != null)
			planFragment.judgeEmptyData();
		if (myFragment != null)
			myFragment.setUnLoginStatus();
		onUnPlanEvent(new UnPlan());
		if (findHouseFragment != null && findHouseFragment.mapFragment != null) {
			findHouseFragment.mapFragment.getLouPanXin(Const.URL_NAME_OF_HOUSES, ZhaoFangFragment.map, false);
		}
	}

	public void onlyGetPlanData() {
		PlansRequest request = new PlansRequest(new Listener<PlansResponse>() {
			@Override
			public void onResponse(PlansResponse response) {
				if (response != null && response.getStatus().equals("1")) {// success
					if (0 == response.getDataList().size()) {
						JiHuaFragment.isNullData = true;
					} else
						JiHuaFragment.isNullData = false;
					if (0 != response.getDataList().size()) {
						// datalist = new String[response.getDataList().size()];
						// JiHuaFragment.datalist = new
						// String[response.getDataList().size()];
						if (JiHuaFragment.datalist == null)
							JiHuaFragment.datalist = new ArrayList<String>();
						JiHuaFragment.datalist.clear();
						for (int i = 0; i < response.getDataList().size(); i++) {
							JiHuaFragment.datalist.add(response.getDataList().get(i).getAppointmentTime());
						}
						if (planFragment != null)
							planFragment.judgeEmptyData();
					}
				}
			}
		}, this);
		request.setToken(PreferenceUtils.getInstance(this).getSettingUserToken());
		request.setAppagent(PreferenceUtils.getInstance(this).getSettingUserAppAgent());
		request.setOsver(PreferenceUtils.getInstance(this).getSettingUserOSVer());
		request.setTransid(PreferenceUtils.getInstance(this).getSettingUserTransId());
		WebUtils.doPost(request);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		closeBar();
	}

	public String[] getDatalist() {
		return datalist;
	}

	public void setDatalist(String[] datalist) {
		this.datalist = datalist;
	}

	private PopQRCourse popQRCourse;

	public void showMyQRCourse() {
		if (popQRCourse == null)
			popQRCourse = new PopQRCourse(INSTANCE, myFragment);
		popQRCourse.showAtLocation(radioGroup, Gravity.NO_GRAVITY, 0, 0);
	}

	public void isShowBottomNavigation(boolean isShow) {
		if (isShow) {
			radioGroup.setVisibility(View.VISIBLE);
			cutLine.setVisibility(View.VISIBLE);
		} else {
			radioGroup.setVisibility(View.GONE);
			cutLine.setVisibility(View.GONE);
		}
	}

	private void setAlias(String alias) {
		// 调用JPush API设置Alias
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
	}

	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				if (Utils.isConnected()) {
					mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
				} else {
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
			}

		}

	};

	private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				if (Utils.isConnected()) {
					mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
				} else {
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
			}
			ToastUtil.show(logs);
		}

	};

	private static final int MSG_SET_ALIAS = 1001;
	private static final int MSG_SET_TAGS = 1002;

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_SET_ALIAS:
				JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
				break;

			case MSG_SET_TAGS:
				JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
				break;

			default:
			}
		}
	};

}
