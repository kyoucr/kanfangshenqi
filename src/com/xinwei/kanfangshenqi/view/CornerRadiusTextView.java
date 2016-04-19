package com.xinwei.kanfangshenqi.view;

import org.xutils.common.util.DensityUtil;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.TextView;

public class CornerRadiusTextView extends TextView {
	private int btnHeight;
	private int roundRadius;
	private int strokeWidth;
	private int bgColor;
	private int strokeColor;
	private GradientDrawable gradientDrawable;
	private Context context;

	public CornerRadiusTextView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		gradientDrawable = new GradientDrawable();// 创建drawable
		// int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd
		// };//分别为开始颜色，中间夜色，结束颜色
		// gradientDrawable = new
		// GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
		try {
			ColorDrawable cd = (ColorDrawable) getBackground();
			if(cd!=null){
				bgColor = cd.getColor();
				gradientDrawable.setColor(bgColor);
			}
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerRadius);
			int n = typedArray.getIndexCount();
			for(int i=0;i<n;i++){
				switch(typedArray.getIndex(i)){
				case R.styleable.CornerRadius_cornerradius_border_color:
					strokeColor = typedArray.getColor(R.styleable.CornerRadius_cornerradius_border_color, 0);
					break;
				case R.styleable.CornerRadius_cornerradius_border_width:
					strokeWidth = (int) typedArray.getDimension(R.styleable.CornerRadius_cornerradius_border_width, 1);
					break;
				}
			}
			gradientDrawable.setStroke(strokeWidth, strokeColor);
			typedArray.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (getHeight() > 0) {
					getViewTreeObserver().removeGlobalOnLayoutListener(this);
					btnHeight = getHeight();
					if (strokeWidth > 0) {
						gradientDrawable.setStroke(strokeWidth, strokeColor);
					}
					initButton();
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
		invalidate();
	}

	public void setStroke(int strokeWidth, int strokeColor) {
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
		gradientDrawable.setStroke(strokeWidth, strokeColor);
		invalidate();
	}
}