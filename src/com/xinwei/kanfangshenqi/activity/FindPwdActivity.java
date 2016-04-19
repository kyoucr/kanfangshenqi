package com.xinwei.kanfangshenqi.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.WoDeFragment;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.request.SpeedinessLoginRequest;
import com.xinwei.kanfangshenqi.response.LoginResponse;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.WebUtils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class FindPwdActivity extends BaseActivity {
	/**
	 * 输入手机号
	 * 
	 */
	@ViewInject(R.id.input_phone)
	private EditText mEditPhone;
	/**
	 * 输入验证码
	 */
	@ViewInject(R.id.input_code)
	private EditText mEditCode;
	/**
	 * 获取验证码
	 */
	@ViewInject(R.id.reget_code)
	private TextView mBtnGetCode;

	private Timer timer;

	private static final int PERIOD = 1;

	private int begin = 60;
	// protected String phString;
	private Bundle data = new Bundle();
	public static FindPwdActivity INSTANCE;
	private String transId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		INSTANCE = this;
		addChildView(R.layout.activity_find_pwd);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt("忘记密码");
		String titleLeft = getIntent().getStringExtra(FindPwdActivity.class.getSimpleName());
		if (titleLeft == null)
			setLeftTitle(R.string.login);
		else
			setLeftTitle(titleLeft);
		setRightTxt("下一步");
		mBtnGetCode.setEnabled(false);
		getRightTxt().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!ValidatorUtil.isValidString(mEditCode.getText().toString())) {
					ToastUtil.show(activity, "请输入验证码！");
					return;
				}
				if (!ValidatorUtil.isMobile(mEditPhone.getText().toString())) {
					ToastUtil.show(activity, "请输入有效的手机号！");
					return;
				}
				doLogin(mEditCode.getText().toString(), mEditPhone.getText().toString());
			}
		});
		mEditPhone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 0) {
					mBtnGetCode.setEnabled(true);
				} else {
					mBtnGetCode.setEnabled(false);
				}
			}
		});
	}

	/**
	 * 获取验证码
	 * 
	 * @param view
	 */
	@Event(R.id.reget_code)
	private void doReGetCodeEvent(View view) {
		if (Utils.checkPhoneNum(mEditPhone.getText().toString())) {
			mBtnGetCode.setText("获取中...");
			mBtnGetCode.setEnabled(false);
			Map<String, String> paramsContent = new HashMap<String, String>();
			paramsContent.put(Const.PARAM_PHONE, mEditPhone.getText().toString());
			paramsContent.put(Const.PARAM_CODETYPE, "2");
			HttpRequest.post(activity, Const.URL_SENDVC, getRequestTag(), paramsContent, new RequestListener() {
				@Override
				public void onSuccess(String url, String responseResult) {
					beginTimer();
					mBtnGetCode.setFocusable(true);
					ToastUtil.show("获取成功, 请查收您的短信");
				}

				@Override
				public void onFailure(String url, String errorInfo) {
					mBtnGetCode.setText(getResources().getString(R.string.get_verification_code));
					mBtnGetCode.setEnabled(true);
				}

				@Override
				public void onError(String url, String responseResult) {
					mBtnGetCode.setText(getResources().getString(R.string.get_verification_code));
					mBtnGetCode.setEnabled(true);
				}
			});
		} else {
			ToastUtil.show("手机号码不合法,请重新输入");
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PERIOD:
				if (begin == 0) {
					begin = 61;
					timer.cancel();
					mBtnGetCode.setText(getResources().getString(R.string.get_verification_code));
					mBtnGetCode.setEnabled(true);
				} else {
					mBtnGetCode.setText(String.valueOf(begin));
				}
				begin--;
				break;
			default:
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
				Log.e("event", "event=" + event);
			}
		}
	};

	/**
	 * 注册
	 *
	 * @param view
	 */
	private void beginTimer() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				mHandler.sendEmptyMessage(PERIOD);
			}
		};
		timer.schedule(task, 0, 1000);
	}


	@Override
	public void requestError(VolleyError error) {
		closeBar();
		ToastUtil.show(activity, getString(R.string.hint_error_net));
	}

	@Override
	protected void onDestroy() {
		INSTANCE = null;
		super.onDestroy();
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return FindPwdActivity.class.getSimpleName();
	}


	private void doLogin(String code, String phone) {
		showBar();
		SpeedinessLoginRequest request = new SpeedinessLoginRequest(new Listener<LoginResponse>() {

			@Override
			public void onResponse(LoginResponse response) {
				closeBar();
				if (response != null && response.getStatus().equals("1")) {
					saveUserInfo(response);
					EventBus.getDefault().post(response);
					if (response.getStatus().equals("1")) {
						data.putString("phone", mEditPhone.getText().toString());
						data.putBoolean("islogin", true);
						if (ChangePWDActivity.INSTANCE != null)
							ChangePWDActivity.INSTANCE.finish();
						Utils.moveTo(FindPwdActivity.this, SetPassWordActivity.class, false, data);
					} else {
						ToastUtil.show(response.getMsg());
					}
				}else{
					ToastUtil.show(response.getMsg());
				}
			}
		}, this);
		request.setAccount(phone);
		request.setCode(code);
		request.setOsver(getUserAgent());
		request.setAppagent(getWlanMacAddress());
		request.setTransid(getRandomNum());
		// request.settelphone("13704041901");
		// request.setPassword("pw123456789");
		// Utils.showProgressDialog(LoginActivity.this);
		WebUtils.doPost(request);
	}

	private String getUserAgent() {
		WebView webview;
		webview = new WebView(this);
		webview.layout(0, 0, 0, 0);
		WebSettings settings = webview.getSettings();
		String ua = settings.getUserAgentString();
		return ua;
	}

	private String getWlanMacAddress() {
		WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
		return m_szWLANMAC;
	}

	private String getRandomNum() {
		int numcode = (int) ((Math.random() * 9 + 1) * 100000);
		transId = "" + numcode;
		return transId;
	}


	private void saveUserInfo(LoginResponse response) {
		PreferenceUtils preferenceUtils = PreferenceUtils.getInstance(getApplicationContext());
		preferenceUtils.setSettingUserNickName(response.getNickName());
		preferenceUtils.setSettingUserPic(response.getHeadPortraitDisplay());
		preferenceUtils.setSettingUserSex(response.getSex());
		preferenceUtils.setSettingUserToken(response.getToken());
		preferenceUtils.setSettingUserAccount(mEditPhone.getText().toString());
		preferenceUtils.setSettingUserNickName(response.getNickName());
		preferenceUtils.setSettingUserTransId(transId);
		preferenceUtils.setSettingUserAppAgent(getWlanMacAddress());
		preferenceUtils.setSettingUserOSVer(getUserAgent());
		if (WoDeFragment.woDeFragment != null)
			WoDeFragment.woDeFragment.onChildViewLoaded();
	}
}
