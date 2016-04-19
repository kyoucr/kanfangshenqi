package com.xinwei.kanfangshenqi.view;

import com.xinwei.kanfangshenqi.util.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ViewBySoftInput extends LinearLayout {
	private OnSoftInputStatusListener onSoftInputStatusListener;

	public ViewBySoftInput(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewBySoftInput(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private boolean mHasInit = false;
	private boolean mHasKeyboard = false;
	private int mHeight;

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (!mHasInit) {
			mHasInit = true;
			mHeight = b;
		} else {
			mHeight = mHeight < b ? b : mHeight;
		}
//		Utils.logCN("mHeight:"+mHeight);
//		Utils.logCN("b:"+b);
//		Utils.logCN("======================================");
		if (mHasInit && mHeight > b) {
			mHasKeyboard = true;
			if (onSoftInputStatusListener != null)
				onSoftInputStatusListener.onShow();
		}
		if (mHasInit && mHasKeyboard && mHeight == b) {
			mHasKeyboard = false;
			if (onSoftInputStatusListener != null)
				onSoftInputStatusListener.onHidden();
		}
	}

	public void setOnSoftInputStatusListener(OnSoftInputStatusListener onSoftInputStatusListener) {
		this.onSoftInputStatusListener = onSoftInputStatusListener;
	}

	public interface OnSoftInputStatusListener {
		public void onHidden();

		public void onShow();
	}
}
