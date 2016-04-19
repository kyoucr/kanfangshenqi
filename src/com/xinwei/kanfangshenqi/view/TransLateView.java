package com.xinwei.kanfangshenqi.view;

import com.xinwei.kanfangshenqi.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/**
 ************************* 
 * pagerview中跟随滑动的标题底部指示线
 * 
 * @author cn 2015-11-5
 ************************* 
 */
public class TransLateView extends View {

	private float x = 0;// 滑动条的起始坐标
	private float initX = 0;// 每个节点的宽度（即：滑动条的宽度）
	Paint paint = new Paint();
	private float fromX = 0;// 距离左侧距离
	private float padding = 0;// 内边距
	private int paintColor;
	private int itemWidth = 0;
	public TransLateView(Context context) {
		super(context);
	}

	public TransLateView(Context context, AttributeSet attribute) {
		super(context, attribute);
	}
	public void setItemWidth(int width){
		this.itemWidth = width;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(paintColor == 0 ? getResources()
				.getColor(R.color.orange) : paintColor);
		paint.setStrokeWidth(15);
		// 绘制滑动条，x为起始坐标，initX + x为终止坐标，就绘制了一条蓝色的线（滑动条）
		canvas.drawLine(fromX + x+padding, 0, fromX + initX + x - padding, 0, paint);
			
	}

	public void setColor(int paintColor) {
		this.paintColor = paintColor;
	}

	/**
	 * 传入要实现滑动的节点数（即：标题数），再根据节点数计算出每一个节点所占的宽度，然后在onDraw函数中绘制。
	 * 
	 * @param count
	 */
	public void setInitX(final int count) {
		// 这是在加载控件时获取控件的总宽度（不一定是屏幕宽度，而是在布局文件中我们设置的：layout_width=“***”的宽度。
		// 然后除以节点数，得到的就是每个节点要显示的长度
		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						initX = (float) getWidth() / count;
						invalidate();
					}
				});

	}

	/**
	 * 得到此控件的宽
	 * 
	 * @return
	 */
	public float getW() {
		invalidate();
		return initX;
	}

	/**
	 * x:绘制滑动条的起始X坐标
	 * 
	 * @param x
	 */
	public void setXx(float x) {
		this.x = x;
		invalidate();
	}

	public void setInitX(float initX) {
		this.initX = initX;
		invalidate();
	}

	public void setFromX(float fromX) {
		this.fromX = fromX;
		invalidate();
	}

	public void setPadding(float padding) {
		this.padding = padding;
		invalidate();
	}
	// 使用方法，在pagerview初始化时，setInitX(int) 设定标题数量，并在下面滑动方法中调用setXx

	/**
	 * 动态绘制滑动条： arg0:表示实在第几个页面，也就是标题。arg0乘以滑动条的宽度，就是没有滑动时滑动条绘制的起始坐标。
	 * arg1:为页面偏移百分比，滑动时，偏移窗口左边的偏移量。偏移量乘以滑动条的宽度再加上，静止时的坐标，就是动态绘制时的起始坐标
	 * 起始坐标确定了，绘制滑动条就交给自定义控件里的onDraw绘制就可以了.
	 * 
	 * @Override public void onPageScrolled(int arg0, float arg1, int arg2) {
	 *           setXx((float) arg0 * move.getW() + move.getW() * arg1); }
	 */
}
