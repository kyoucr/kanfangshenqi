package com.xinwei.kanfangshenqi.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.fragment.WoDeFragment;
import com.xinwei.kanfangshenqi.util.DensityUtil;
import com.xinwei.kanfangshenqi.util.Utils;

public class PopQRCourse extends PopupWindow {
	public PopQRCourse(Activity activity,WoDeFragment myFragment) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.pop_myqr_course, null);
		View rltCenter = view.findViewById(R.id.rltCenter);
		View viewTop = view.findViewById(R.id.viewTop);
		int[] location = new int[2];
		myFragment.getQRView().getLocationOnScreen(location);  
        int x = location[0];  
        int y = location[1];
		RelativeLayout.LayoutParams paramsViewTop = (LayoutParams) viewTop.getLayoutParams();
		RelativeLayout.LayoutParams params = (LayoutParams) rltCenter.getLayoutParams();
		params.height = myFragment.getQRView().getHeight()+70;
		rltCenter.setLayoutParams(params);
//		params.topMargin = y+DensityUtil.dip2px(activity, 45)-params.height/3;
		paramsViewTop.height = y+DensityUtil.dip2px(activity, 45)-params.height/3;
		viewTop.setLayoutParams(paramsViewTop);
		setContentView(view);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				dismiss();
				return false;
			}
		});
		this.setFocusable(true);
		this.setOutsideTouchable(false);
	}
}
