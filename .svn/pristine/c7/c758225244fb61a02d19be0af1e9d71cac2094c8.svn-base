package com.xinwei.kanfangshenqi.fragment;

import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.R;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class GuideFragment1 extends BaseFragment {
	@ViewInject(R.id.imgPerson)
	private ImageView imgPerson;
	@ViewInject(R.id.imgCalender21)
	private ImageView imgCalender21;
	@ViewInject(R.id.imgCalender13)
	private ImageView imgCalender13;
	@ViewInject(R.id.imgCalender18)
	private ImageView imgCalender18;
	@ViewInject(R.id.imgLonghu)
	private ImageView imgLonghu;
	@ViewInject(R.id.imgZhonghai)
	private ImageView imgZhonghai;
	@ViewInject(R.id.imgWanke)
	private ImageView imgWanke;
	private boolean isLoaded;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.fragment_guide1);
	}

	public void reload() {
		if(isLoaded)
			onChildViewLoaded();
	}

	public void hideView() {
		if (imgCalender21 != null) {
			imgCalender21.setVisibility(View.INVISIBLE);
			imgCalender13.setVisibility(View.INVISIBLE);
			imgCalender18.setVisibility(View.INVISIBLE);
			imgLonghu.setVisibility(View.INVISIBLE);
			imgZhonghai.setVisibility(View.INVISIBLE);
			imgWanke.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onChildViewLoaded() {
		isLoaded = true;
		hideView();
		imgPerson.setBackgroundResource(R.anim.guide1_people_anim);
		AnimationDrawable mLoadAnim = (AnimationDrawable) imgPerson
				.getBackground();
		mLoadAnim.start();
		Animation animation1 = AnimationUtils.loadAnimation(activity,
				R.anim.guide_alpha);
		final Animation animation2 = AnimationUtils.loadAnimation(activity,
				R.anim.guide_alpha);
		animation1.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				imgLonghu.startAnimation(animation2);
				imgCalender13.startAnimation(animation2);
				imgWanke.setVisibility(View.VISIBLE);
				imgCalender21.setVisibility(View.VISIBLE);
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
				imgLonghu.setVisibility(View.VISIBLE);
				imgCalender13.setVisibility(View.VISIBLE);
				Animation animation3 = AnimationUtils.loadAnimation(activity,
						R.anim.guide_alpha);
				animation3.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						imgZhonghai.setVisibility(View.VISIBLE);
						imgCalender18.setVisibility(View.VISIBLE);
					}
				});
				imgZhonghai.startAnimation(animation3);
				imgCalender18.startAnimation(animation3);
			}
		});
		imgWanke.startAnimation(animation1);
		imgCalender21.startAnimation(animation1);
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
