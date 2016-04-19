package com.xinwei.kanfangshenqi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewInScroll extends ListView {
	private boolean isAuto = true;

	public ListViewInScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewInScroll(Context context) {
		super(context);
	}

	public ListViewInScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (isAuto) {
			int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	
}
