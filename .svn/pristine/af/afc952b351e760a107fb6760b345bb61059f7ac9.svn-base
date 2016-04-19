package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.king.photo.activity.PublishActivity;
import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.FaxianFragment;
import com.xinwei.kanfangshenqi.fragment.housecircle.AskFragment;
import com.xinwei.kanfangshenqi.fragment.housecircle.InterestFragment;
import com.xinwei.kanfangshenqi.fragment.housecircle.RemindFragment;
import com.xinwei.kanfangshenqi.model.Discovery;
import com.xinwei.kanfangshenqi.util.DensityUtil;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;
import com.xinwei.kanfangshenqi.view.StickyLayout;
import com.xinwei.kanfangshenqi.view.StickyLayout.OnGiveUpTouchEventListener;

import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SeeHouseCircleActivity extends BaseFragmentActivity implements OnGiveUpTouchEventListener{
	@ViewInject(R.id.stickyLayout)
	public StickyLayout stickyLayout;
	@ViewInject(R.id.lltAsk)
	private LinearLayout lltAsk;
	@ViewInject(R.id.lltInterest)
	private LinearLayout lltInterest;
	@ViewInject(R.id.lltRemind)
	private LinearLayout lltRemind;
	@ViewInject(R.id.btnPublish)
	private TextView btnPublish;
	@ViewInject(R.id.rltLeft)
	private RelativeLayout rltLeft;
	@ViewInject(R.id.imgHead)
	private CircleImageViewWithBorder imgHead;
	@ViewInject(R.id.txtNickname)
	private TextView txtNickname;
	@ViewInject(R.id.txtAskNum)
	private TextView txtAskNum;
	@ViewInject(R.id.txtInterestNum)
	private TextView txtInterestNum;
	@ViewInject(R.id.txtRemindNum)
	private TextView txtRemindNum;
	@ViewInject(R.id.imgArrow)
	private ImageView imgArrow;
	private BaseFragment currentFragment;
	private BaseFragment askFragment;
	private BaseFragment interestFragment;
	private BaseFragment remindFragment;
	public static SeeHouseCircleActivity INSTANCE;
	private OnPublishedListener onPublishedListener = new OnPublishedListener() {

		@Override
		public void onPulished() {
			if (askFragment != null) {
				askFragment.onReloadData();
			}
		}
	};
	public static OnPublishedListener OnPublishedListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShowTitle(false);
		INSTANCE = this;
		addChildView(R.layout.activity_see_house_circle_new);
	}
	@Override
	protected void onStart() {
		super.onStart();
		Utils.hideKeyboard(INSTANCE);
	}
	@Event({ R.id.lltAsk, R.id.lltInterest, R.id.lltRemind, R.id.rltSubLeft,
			R.id.btnPublish })
	private void onClickEvent(View v) {
		if (v.getId() == R.id.rltSubLeft) {
			finish();
			return;
		}
		if (v.getId() == R.id.btnPublish) {
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			OnPublishedListener = onPublishedListener;
			Utils.moveTo(activity, PublishActivity.class);
			return;
		}
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		hideAll(ft);
		switch (v.getId()) {
		case R.id.lltAsk:
			btnPublish.setEnabled(true);
			if (askFragment == null)
				askFragment = new AskFragment();
			if (!askFragment.isAdded())
				ft.add(R.id.frame, askFragment);
			ft.show(askFragment);
			currentFragment = askFragment;
			startMarkAnim(Utils.getDeviceWidth(activity) / 6
					- DensityUtil.dip2px(activity, 10));
			break;
		case R.id.lltInterest:
			btnPublish.setEnabled(false);
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			if (interestFragment == null)
				interestFragment = new InterestFragment();
			if (!interestFragment.isAdded())
				ft.add(R.id.frame, interestFragment);
			ft.show(interestFragment);
			currentFragment = interestFragment;
			startMarkAnim(Utils.getDeviceWidth(activity) / 2
					- DensityUtil.dip2px(activity, 10));
			break;
		case R.id.lltRemind:
			btnPublish.setEnabled(false);
			if (null == Utils.getHeaderParamsOnly()) {
				getAlert().showLogin();
				return;
			}
			if (remindFragment == null)
				remindFragment = new RemindFragment();
			if (!remindFragment.isAdded())
				ft.add(R.id.frame, remindFragment);
			ft.show(remindFragment);
			currentFragment = remindFragment;
			startMarkAnim((Utils.getDeviceWidth(activity) / 6) * 5
					- DensityUtil.dip2px(activity, 10));
			break;
		}
		ft.commit();
	}

	private void startMarkAnim(final int endX) {
		ObjectAnimator oa = ObjectAnimator.ofFloat(imgArrow, "translationX",
				imgArrow.getX(), endX).setDuration(300);
		oa.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(android.animation.Animator animation) {
			}

			@Override
			public void onAnimationRepeat(android.animation.Animator animation) {
			}

			@Override
			public void onAnimationEnd(android.animation.Animator animation) {
				imgArrow.setX(endX);
			}

			@Override
			public void onAnimationCancel(android.animation.Animator animation) {
			}
		});
		oa.start();
	}

	private void hideAll(FragmentTransaction ft) {
		if (askFragment != null && askFragment.isAdded())
			ft.hide(askFragment);
		if (interestFragment != null && interestFragment.isAdded())
			ft.hide(interestFragment);
		if (remindFragment != null && remindFragment.isAdded())
			ft.hide(remindFragment);
	}

	@Override
	public void onChildViewLoaded() {
		stickyLayout.setOnGiveUpTouchEventListener(this);
		setUserInfoView();
		imgArrow.setX(Utils.getDeviceWidth(activity) / 6
				- DensityUtil.dip2px(activity, 10));
		Discovery discovery = (Discovery) getIntent().getSerializableExtra(
				Discovery.class.getSimpleName());
		if (discovery == null
				|| !ValidatorUtil.isValidString(discovery.getForwardType()))
			onClickEvent(lltAsk);
		else {
			if (Const.ASK_TYPE.equals(discovery.getForwardType())) {
				onClickEvent(lltAsk);
			} else if (Const.INTEREST_TYPE.equals(discovery.getForwardType())) {
				onClickEvent(lltInterest);
			} else if (Const.REMIND_TYPE.equals(discovery.getForwardType())) {
				onClickEvent(lltRemind);
			}
		}
	}

	public void setUserInfoView() {
		PreferenceUtils preferenceUtils = PreferenceUtils.getInstance(this);
		ImageLoaderUtil.getInstance().bindHeadImg(imgHead, preferenceUtils.getSettingUserPic());
		if (Utils.getHeaderParamsOnly() != null) {
			// 设置用户信息
			String nickname = preferenceUtils.getSettingUserNickName();
			TextViewWriterUtil.writeValue(txtNickname, nickname);
		}else{
			TextViewWriterUtil.writeValue(txtNickname, "未登录");
		}
	}

	public void setNavigationNum(String askNum, String interestNum,
			String remindNum) {
		if (ValidatorUtil.isValidString(askNum))
			TextViewWriterUtil.writeValue(txtAskNum, askNum);
		if (ValidatorUtil.isValidString(interestNum))
			TextViewWriterUtil.writeValue(txtInterestNum, interestNum);
		if (ValidatorUtil.isValidString(remindNum))
			TextViewWriterUtil.writeValue(txtRemindNum, remindNum);
	}

	public void reduceInterestNum() {
		if (ValidatorUtil.isValidString(txtInterestNum.getText().toString()
				.trim())
				&& Integer.parseInt(txtInterestNum.getText().toString().trim()) > 0)
			TextViewWriterUtil.writeValue(
					txtInterestNum,
					String.valueOf(Integer.parseInt(txtInterestNum.getText()
							.toString().trim()) - 1));
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return SeeHouseCircleActivity.class.getSimpleName();
	}

	@Override
	public void onBackPressed() {
		if (!currentFragment.onBackPressed())
			finish();
	}

	@Override
	protected void onDestroy() {
		INSTANCE = null;
		if (FaxianFragment.rltNewUpdate != null) {
			FaxianFragment.rltNewUpdate.setVisibility(View.GONE);
		}
		Utils.setSeeHouseCircleRequestTime(String.valueOf(System.currentTimeMillis() / 1000));
		OnPublishedListener = null;
		super.onDestroy();
	}

	public interface OnPublishedListener {
		public void onPulished();
	}

	@Override
	public boolean giveUpTouchEvent(MotionEvent event) {
		return !currentFragment.isControlling();
	}
}
