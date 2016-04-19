package com.xinwei.kanfangshenqi.fragment;

import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class GuideFragment2 extends BaseFragment {
	@ViewInject(R.id.imgMoney)
	private ImageView imgMoney;
	@ViewInject(R.id.imgCloud1)
	private ImageView imgCloud1;
	@ViewInject(R.id.imgCloud4)
	private ImageView imgCloud4;
	@ViewInject(R.id.imgCloud2)
	private ImageView imgCloud2;
	@ViewInject(R.id.imgCloud3)
	private ImageView imgCloud3;
	private boolean isLoaded;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_guide2);
	}
	public void reload() {
		if(isLoaded)
			onChildViewLoaded();
	}
	@Override
	public void onChildViewLoaded() {
		isLoaded = true;
		imgMoney.setBackgroundResource(R.anim.guide2_money_anim);
		AnimationDrawable mLoadAnim = (AnimationDrawable) imgMoney.getBackground();
		mLoadAnim.start();
		TranslateAnimation animation = new TranslateAnimation(0, -100, 0, 0);
		animation.setDuration(2000);
		animation.setRepeatCount(Integer.MAX_VALUE);
		animation.setRepeatMode(Animation.REVERSE);
		imgCloud1.startAnimation(animation);
		imgCloud4.startAnimation(animation);
		TranslateAnimation animation2 = new TranslateAnimation(0, 100, 0, 0);
		animation2.setDuration(2000);
		animation2.setRepeatCount(Integer.MAX_VALUE);
		animation2.setRepeatMode(Animation.REVERSE);
		imgCloud2.startAnimation(animation2);
		imgCloud3.startAnimation(animation2);
	}
	@Override
	public void onReloadData() {
		
	}
	@Override
	public boolean onBackPressed() {
		return false;
	}
	@Override
	public String getRequestTag() {
		return null;
	}
}
