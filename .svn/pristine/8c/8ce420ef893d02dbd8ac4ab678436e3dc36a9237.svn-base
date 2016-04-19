package com.xinwei.kanfangshenqi.fragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.PreferenceActivity;
import com.xinwei.kanfangshenqi.util.Utils;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class GuideFragment3 extends BaseFragment {
	@ViewInject(R.id.imgPhone)
	private ImageView imgPhone;
	@ViewInject(R.id.imgDialog1)
	private ImageView imgDialog1;
	@ViewInject(R.id.imgDialog2)
	private ImageView imgDialog2;
	@ViewInject(R.id.imgDialog3)
	private ImageView imgDialog3;
	@ViewInject(R.id.imgDialog4)
	private ImageView imgDialog4;
	private boolean isLoaded;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_guide3);
	}
	public void reload() {
		if(isLoaded)
			onChildViewLoaded();
	}
	public void hideView(){
		if(imgDialog1!=null){
			imgDialog1.setVisibility(View.INVISIBLE);
			imgDialog2.setVisibility(View.INVISIBLE);
			imgDialog3.setVisibility(View.INVISIBLE);
			imgDialog4.setVisibility(View.INVISIBLE);
		}
	}
	@Override
	public void onChildViewLoaded() {
		isLoaded = true;
		hideView();
		imgPhone.setBackgroundResource(R.anim.guide3_phone_anim);
		AnimationDrawable mLoadAnim = (AnimationDrawable) imgPhone.getBackground();
		mLoadAnim.start();
		Animation animation1 = AnimationUtils.loadAnimation(activity, R.anim.guide_alpha);
		final Animation animation2 = AnimationUtils.loadAnimation(activity, R.anim.guide_alpha);
		final Animation animation3 = AnimationUtils.loadAnimation(activity, R.anim.guide_alpha);
		animation1.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				imgDialog2.setVisibility(View.VISIBLE);
				imgDialog1.startAnimation(animation2);
			}
		});
		animation2.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				imgDialog1.setVisibility(View.VISIBLE);
				imgDialog3.startAnimation(animation3);
			}
		});
		animation3.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				imgDialog3.setVisibility(View.VISIBLE);
				Animation animation4 = AnimationUtils.loadAnimation(activity, R.anim.guide_alpha);
				animation4.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						imgDialog4.setVisibility(View.VISIBLE);
					}
				});
				imgDialog4.startAnimation(animation4);
			}
		});
		imgDialog2.startAnimation(animation1);
	}
	@Event(R.id.btnExperience)
	private void onClickEvent(View v){
		Utils.moveTo(activity, PreferenceActivity.class, true, null);
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
