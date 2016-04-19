package com.xinwei.kanfangshenqi.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetricsInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class TextViewNoBottomPadding extends TextView {

	public TextViewNoBottomPadding(Context context) {
		super(context);
	}

	public TextViewNoBottomPadding(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextViewNoBottomPadding(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	FontMetricsInt fontMetricsInt;

	@Override
	protected void onDraw(Canvas canvas) {
		if (fontMetricsInt == null) {
			fontMetricsInt = new FontMetricsInt();
			getPaint().getFontMetricsInt(fontMetricsInt);
			
		}
		
//		Utils.logCN("fontMetricsInt.top:"+fontMetricsInt.top);
//		Utils.logCN("fontMetricsInt.ascent:"+fontMetricsInt.ascent);
//		Utils.logCN("fontMetricsInt.leading:"+fontMetricsInt.leading);
//		Utils.logCN("fontMetricsInt.descent:"+fontMetricsInt.descent);
//		Utils.logCN("fontMetricsInt.bottom:"+fontMetricsInt.bottom);
//		Utils.logCN("Gravity.TOP:"+Gravity.TOP);
//		Utils.logCN("Gravity.BOTTOM:"+Gravity.BOTTOM);
//		if(getGravity()==Gravity.TOP){
//			canvas.translate(0, fontMetricsInt.ascent-fontMetricsInt.leading);
//			canvas.translate(0, fontMetricsInt.top - fontMetricsInt.ascent);
//		}else if(getGravity()==Gravity.BOTTOM){
			canvas.translate(0, fontMetricsInt.descent-fontMetricsInt.leading-(fontMetricsInt.bottom-fontMetricsInt.descent));
//		}
		super.onDraw(canvas);
	}
}
