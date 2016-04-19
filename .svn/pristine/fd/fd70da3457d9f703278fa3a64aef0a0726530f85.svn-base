package com.xinwei.kanfangshenqi.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.R;

public class TitleBarView extends LinearLayout {

	private AttributeSet mAttrs;
	private Context mContext;
	private TitleBarListerer mListener;
	private View leftView;
	private View rightView;
	private RelativeLayout mRLayout;
	private int dip5;
	private int dip2;

	public void setListener(TitleBarListerer mListener) {
		this.mListener = mListener;
	}

	public TitleBarView(Context context) {
		super(context);
	}

	public TitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mAttrs = attrs;
		mContext = context;
		dip5 = Utils.dip2px(mContext, 5);
		dip2 = Utils.dip2px(mContext, 2);
		initView();
	}

	private void initView() {
		// this.setBackgroundResource(R.drawable.title_bg);
		mRLayout = new RelativeLayout(mContext);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundColor(Color.rgb(250, 250, 250));
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);

		int titleBarHeight;
		if (Utils.getDeviceHeight((Activity)mContext) != 0) {
			titleBarHeight = Utils.getDeviceHeight((Activity)mContext) / 12;
		}else{
			titleBarHeight = Utils.dip2px(mContext, (int) getResources().getDimension(
					R.dimen.title_bar_height));
		}
		
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, titleBarHeight);
		this.addView(mRLayout, ll);

		if (mAttrs != null) {
			TypedArray typedArray = mContext.obtainStyledAttributes(mAttrs,
					R.styleable.TitleBarElement);

			int resLeft = typedArray.getResourceId(
					R.styleable.TitleBarElement_leftImage, -1);
			String textLeft = typedArray
					.getString(R.styleable.TitleBarElement_lefttext);
			addLeftComponent(resLeft, textLeft);

			int resRight = typedArray.getResourceId(
					R.styleable.TitleBarElement_rightImage, -1);
			String textRight = typedArray
					.getString(R.styleable.TitleBarElement_righttext);
			addRightComponent(resRight, textRight);

			String title = typedArray
					.getString(R.styleable.TitleBarElement_mtitle);
			if (!StringUtils.isEmpty(title)) {
				addTitle(title);
			}
			typedArray.recycle();
		}
	}

	private void addRightComponent(int resRight, String textRight) {
		TextView tv = new TextView(mContext);
		rightView = tv;
		if (!StringUtils.isEmpty(textRight)) {
			tv.setText(textRight);
			tv.setTextColor(mContext.getResources().getColorStateList(R.color.orange));
			tv.setPadding(dip2, dip5, dip2, dip5);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		} else if (resRight != -1) {
			tv.setBackgroundResource(resRight);
		}
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.rightMargin = Utils.dip2px(mContext, 14);;
		tv.setLayoutParams(lp);
		tv.setPadding(0, 0, 0, 0);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mListener != null) {
					mListener.onClickRightComponent();
				}
			}
		});
		mRLayout.addView(tv);
	}
    private void isShowLine(){
    	View v= (View) this.findViewWithTag("bottom_line");
    	if(v==null){
    		View view = new View(mContext);
    		view.setBackgroundColor(mContext.getResources().getColor(R.color.divider_light_gray));
    		view.setTag("bottom_line");
    		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
    				RelativeLayout.LayoutParams.MATCH_PARENT,
    				1);
    		rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    		view.setLayoutParams(rl);
    		mRLayout.addView(view);
    	}else{
    		mRLayout.addView(v);
    	}
    }
	private void addTitle(String title) {
		if (!StringUtils.isEmpty(title)) {
			TextView tv = (TextView) this.findViewWithTag("tv_title");
			if (tv == null) {
				TextView tvTitle = new TextView(mContext);
				tvTitle.setText(title);
				tvTitle.setTag("tv_title");
				tvTitle.setId(500);
				tvTitle.setTextColor(mContext.getResources().getColorStateList(R.color.black));
				tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
				RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				rl.addRule(RelativeLayout.CENTER_IN_PARENT);
				// rl.leftMargin = 100;
				// rl.rightMargin = 10;
				tvTitle.setPadding(0, 0, 0, 0);
				tvTitle.setLayoutParams(rl);
				tvTitle.setSingleLine(true);
				tvTitle.setEllipsize(TruncateAt.END);
				mRLayout.addView(tvTitle);
			} else {
				tv.setText(title);
			}
		}
	}

	public void setTitle(String title) {
		addTitle(title);
	}

	private void addLeftComponent(int resLeft, String texgLefgt) {
		TextView tv = new TextView(mContext);
		leftView = tv;
		if (!StringUtils.isEmpty(texgLefgt)) {
			tv.setText(texgLefgt);
			tv.setPadding(dip2, dip5, dip2, dip5);
		} else if (resLeft != -1) {
			tv.setBackgroundResource(resLeft);
		}
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				Utils.dip2px(mContext, 24),
				Utils.dip2px(mContext, 24));
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.leftMargin = Utils.dip2px(mContext, 14);
		tv.setLayoutParams(lp);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mListener != null) {
					mListener.onClickLeftComponent();
				}
			}
		});
		mRLayout.addView(tv);
	}
	public void setLeftView(int resLeft, String texgLefgt) {
		addLeftComponent(resLeft, texgLefgt);
	}

	public void setRightView(int resRight, String textRight) {
		addRightComponent(resRight, textRight);
	}
	public void setShowLine() {
		isShowLine();
	}
	public View getLeftView() {
		return leftView;
	}

	public View getRightView() {
		return rightView;
	}

	public interface TitleBarListerer {
		void onClickLeftComponent();

		void onClickRightComponent();
	}
}
