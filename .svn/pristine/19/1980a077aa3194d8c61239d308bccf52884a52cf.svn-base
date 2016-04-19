package com.xinwei.kanfangshenqi.view;

import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.StringUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CircleImageView extends FrameLayout implements ImageAware {
	private Bitmap bitmap;
	private AttributeSet mAttrs;
	private Context mContext;

	public CircleImageView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	private void init() {

		if (mAttrs != null) {
			TypedArray typedArray = mContext.obtainStyledAttributes(mAttrs,
					R.styleable.CircleImageView);

			String text = typedArray
					.getString(R.styleable.CircleImageView_imagetext);
			if (!StringUtils.isEmpty(text)) {
				TextView textView = new TextView(mContext);
				textView.setText(text);
				textView.setTextColor(mContext.getResources().getColor(
						R.color.white));
				float demension = typedArray.getDimension(
						R.styleable.CircleImageView_imagetextSize, -1);
				if (-1 != demension) {
					textView.setTextSize(demension);
				}
				float demensionBottom = typedArray.getDimension(
						R.styleable.CircleImageView_bottomMargin, -1);
				if (-1 != demensionBottom) {
					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
							FrameLayout.LayoutParams.WRAP_CONTENT,
							FrameLayout.LayoutParams.WRAP_CONTENT);
					params.bottomMargin = (int)demensionBottom;
					params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
					textView.setLayoutParams(params);
				}
				this.addView(textView);
			}
			typedArray.recycle();
		}
		
		/*this.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				bitmap = ((BitmapDrawable) (CircleImageView.this
						.getBackground())).getBitmap();
				if (arg1.getX() >= bitmap.getWidth()
						|| arg1.getY() >= bitmap.getHeight()) {
					return true;
				}
				if ((int) (arg1.getX()) >= 0 && (int) (arg1.getY()) >= 0) {
					if (bitmap.getPixel((int) (arg1.getX()),
							((int) arg1.getY())) == 0) {
						return true;// 透明区域返回true
					}
				}
				return false;
			}
		});*/
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		mAttrs = attrs;
		init();
	}

	@Override
	public ViewScaleType getScaleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getWrappedView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setImageBitmap(Bitmap arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setImageDrawable(Drawable arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}