package com.xinwei.kanfangshenqi.fragment;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment2;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AchievementActivity;
import com.xinwei.kanfangshenqi.activity.CashRedPacketActivity;
import com.xinwei.kanfangshenqi.activity.LoginActivity;
import com.xinwei.kanfangshenqi.activity.MainActivity;
import com.xinwei.kanfangshenqi.activity.MyQRActivity;
import com.xinwei.kanfangshenqi.activity.NotifyActivity;
import com.xinwei.kanfangshenqi.activity.PublishGiftActivity;
import com.xinwei.kanfangshenqi.activity.SettingActivity;
import com.xinwei.kanfangshenqi.activity.SuggestionFeedbackActivity;
import com.xinwei.kanfangshenqi.activity.UserInfoActivity;
import com.xinwei.kanfangshenqi.adapter.ShareAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.response.LoginResponse;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.util.XmlUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class WoDeFragment extends BaseFragment2 {

	@ViewInject(R.id.photo)
	private CircleImageViewWithBorder photo;
	@ViewInject(R.id.imgSex)
	private ImageView imgSex;
	@ViewInject(R.id.txtNickname)
	private TextView txtNickname;
	@ViewInject(R.id.ll_name)
	private LinearLayout ll_name;
	@ViewInject(R.id.iv_qr_code)
	private View iv_qr_code;
	@ViewInject(R.id.txtLogout)
	private TextView txtLogout;
	private PreferenceUtils preferenceUtils;
	public static WoDeFragment woDeFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		addChildView(R.layout.fragment_wode);
		woDeFragment = this;
	}
	@Subscribe
	public void onLoginEvent(LoginResponse loginResponse){
		onChildViewLoaded();
	}
	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.user);
		isShowTitle(true);
		isShowLeft(false);
		preferenceUtils = PreferenceUtils.getInstance(getActivity());
		ImageLoaderUtil.getInstance().bindHeadImg(photo, preferenceUtils.getSettingUserPic());
		// 获取用户信息
		if (ValidatorUtil.isValidString(preferenceUtils.getSettingUserToken())) {
			ll_name.setVisibility(View.VISIBLE);
			txtLogout.setVisibility(View.INVISIBLE);
			String nickname = preferenceUtils.getSettingUserNickName();
			TextViewWriterUtil.writeValue(txtNickname, ValidatorUtil.isValidString(nickname)?nickname:getString(R.string.txt_default_nickname));
			String sex = preferenceUtils.getSettingUserSex();
			if (Const.MAN.equals(sex))
				imgSex.setImageResource(R.drawable.man_icon);
			else if (Const.WOMAN.equals(sex))
				imgSex.setImageResource(R.drawable.woman_icon);
			else
				imgSex.setImageResource(R.drawable.ic_mars_in_my);
		} else {
			ll_name.setVisibility(View.INVISIBLE);
			txtLogout.setVisibility(View.VISIBLE);
			photo.setImageResource(R.drawable.not_logged_in_headpic);
		}
		XmlUtil xmlUtil = new XmlUtil(Const.USER_INFO);
		if (isPrepared
				&& !ValidatorUtil.isValidString(xmlUtil
						.get(Const.IS_FIRST_USE_MY_FRAGMENT))) {
			MainActivity.INSTANCE.showMyQRCourse();
			xmlUtil.set(Const.IS_FIRST_USE_MY_FRAGMENT, Const.YES);
		}
	}
	public void setUnLoginStatus(){
		if(ll_name!=null){
			ll_name.setVisibility(View.INVISIBLE);
			txtLogout.setVisibility(View.VISIBLE);
			photo.setImageResource(R.drawable.not_logged_in_headpic);
		}
	}
	@Event({ R.id.rltRedPacket, R.id.photo, R.id.txtLogout,
			R.id.suggestionFeedback, R.id.txtAchievement, R.id.lltQR,
			R.id.rltPublishGift, R.id.rltShare, R.id.rltNotify, R.id.rltSetting })
	private void toItemEventEvent(View v) {
		switch (v.getId()) {
		case R.id.photo:
			if (null == Utils.getHeaderParamsOnly()) {
				Bundle data = new Bundle();
				data.putBoolean(Const.FLAG_UNLOGIN, true);
				Utils.moveTo(MainActivity.INSTANCE, LoginActivity.class, false,
						data);
				return;
			}
			Intent intent = new Intent();
			intent.setClass(getActivity(), UserInfoActivity.class);
			startActivityForResult(intent, 12);
			break;
		case R.id.txtLogout:
			Bundle data = new Bundle();
			data.putBoolean(Const.FLAG_UNLOGIN, true);
			Utils.moveTo(getActivity(), LoginActivity.class, false, data);
			break;
		case R.id.rltRedPacket:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			data = new Bundle();
			data.putString(Const.WEB_LEFT_TITLE_KEY, getString(R.string.user));
			Utils.moveTo(activity, CashRedPacketActivity.class,false,data);
			break;
		case R.id.suggestionFeedback:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Utils.moveTo(activity, SuggestionFeedbackActivity.class);
			break;
		case R.id.txtAchievement:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Utils.moveTo(activity, AchievementActivity.class);
			break;
		case R.id.lltQR:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Utils.moveTo(activity, MyQRActivity.class);
			break;
		case R.id.rltPublishGift:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Utils.moveTo(activity, PublishGiftActivity.class);
			break;
		case R.id.rltShare:
			showShare();
			break;
		case R.id.rltNotify:
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			Utils.moveTo(activity, NotifyActivity.class);
			break;
		case R.id.rltSetting:
			Utils.moveTo(activity, SettingActivity.class);
			break;
		}
	}

	private PopupWindow pop;
	private View view;
	private GridView gridView;
	private void showShare() {
		if (pop == null) {
			view = LayoutInflater.from(activity).inflate(R.layout.pop_share,
					null);
			gridView = (GridView) view
					.findViewById(R.id.gridViewShare);
			final List<String> shareList = new ArrayList<String>();
			shareList.add(getString(R.string.txt_wx_friend));
			shareList.add(getString(R.string.txt_circle_friend));
			shareList.add(getString(R.string.txt_sms));
			shareList.add(getString(R.string.txt_qq_friend));
			shareList.add(getString(R.string.txt_qq_space));
			shareList.add(getString(R.string.txt_xin_lang));
			pop = new PopupWindow(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			pop.setContentView(view);
			pop.setBackgroundDrawable(new BitmapDrawable());
			ShareAdp shareAdp = new ShareAdp(activity, shareList,pop);
			gridView.setAdapter(shareAdp);
			view.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					pop.dismiss();
//					animEndPop();
					return false;
				}
			});
		}
		gridView.startAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.pop_share_show_anim));
//		pop.setAnimationStyle(R.style.pop_share_anim_style);
		pop.showAtLocation(parentView, Gravity.BOTTOM
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}
	
	private void animEndPop(){
		if (pop != null && pop.isShowing()) {
			Animation anim = AnimationUtils.loadAnimation(
					getActivity(), R.anim.pop_share_hidden_anim);
			gridView.startAnimation(anim);
			anim.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
				}
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					pop.dismiss();				
				}
			});
		}
	}
	@Override
	public void onReloadData() {
	}

	@Override
	public boolean onBackPressed() {
		if (pop != null && pop.isShowing()) {
			pop.dismiss();
//			animEndPop();
			return true;
		}
		return false;
	}
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(!hidden&&Utils.getHeaderParamsOnly()!=null)
			ImageLoaderUtil.getInstance().bindHeadImg(photo, preferenceUtils.getSettingUserPic());
	}
	@Override
	public String getRequestTag() {
		return WoDeFragment.class.getSimpleName();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			EventBus.getDefault().unregister(this);
		} catch (Exception e) {
		}
		woDeFragment = null;
		imgSex = null;
		txtNickname = null;
		photo = null;
		ll_name = null;
		txtLogout = null;
	}

	public View getQRView() {
		return iv_qr_code;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			getActivity().runOnUiThread(new Runnable() {
				public void run() {
					// 设置用户信息
					ImageLoaderUtil.getInstance().bindHeadImg(photo, preferenceUtils.getSettingUserPic());
					String nickname = preferenceUtils.getSettingUserNickName();
					TextViewWriterUtil.writeValue(txtNickname, nickname);
					String sex = preferenceUtils.getSettingUserSex();
					if (Const.MAN.equals(sex))
						imgSex.setImageResource(R.drawable.man_icon);
					else if (Const.WOMAN.equals(sex))
						imgSex.setImageResource(R.drawable.woman_icon);
					else
						imgSex.setImageResource(R.drawable.ic_mars);
				}
			});
		}
	}

}
