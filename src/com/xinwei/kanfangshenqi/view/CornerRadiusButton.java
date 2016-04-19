package com.xinwei.kanfangshenqi.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;

public class CornerRadiusButton extends Button {
	private int btnHeight;
	private int roundRadius;
	private int strokeWidth;
	private int bgColor;
	private int strokeColor;
	private GradientDrawable gradientDrawable;

	public CornerRadiusButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		gradientDrawable = new GradientDrawable();// 创建drawable
//		int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };//分别为开始颜色，中间夜色，结束颜色
//		gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
		try {
			ColorDrawable cd = (ColorDrawable) getBackground();
			bgColor = cd.getColor();
			gradientDrawable.setColor(bgColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (getHeight() > 0) {
					btnHeight = getHeight();
					initButton();
					getViewTreeObserver().removeGlobalOnLayoutListener(this);
				}
			}
		});
	}

	private void initButton() {
		this.roundRadius = btnHeight / 2; // 圆角半径
		gradientDrawable.setCornerRadius(roundRadius);
		setBackgroundDrawable(gradientDrawable);
	}

	public void setBackgroundColor(int bgColor) {
		this.bgColor = bgColor;
		gradientDrawable.setColor(bgColor);
	}

	public void setStroke(int strokeWidth, int strokeColor) {
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
		gradientDrawable.setStroke(strokeWidth, strokeColor);
	}
}
