package com.xinwei.kanfangshenqi.view;

import java.util.ArrayList;
import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil.OnLoadProgressListener;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ImgDetailPop extends PopupWindow {
	private View view;
	private ExtendedViewPager viewPager;
	private TextView txtIndex;
	private List<String> listImg;
	private List<View> views;
	private Activity activity;
	private int index;

	public ImgDetailPop(Activity activity, List<String> listImg, int index) {
		this.activity = activity;
		this.listImg = listImg;
		this.index = index;
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		view = LayoutInflater.from(activity).inflate(R.layout.item_img_detail, null);
		viewPager = (ExtendedViewPager) view.findViewById(R.id.viewPager);
		txtIndex = (TextView) view.findViewById(R.id.txtIndex);
		setAnimationStyle(R.style.AnimationFade);
		setContentView(view);
		setBackgroundDrawable(new BitmapDrawable());
		bindView();
	}

	public void bindView() {
		views = new ArrayList<View>();
		txtIndex.setText(activity.getString(R.string.txt_index, index + 1, listImg.size()));
		for (int i = 0; i < listImg.size(); i++) {
			View v = LayoutInflater.from(activity).inflate(R.layout.item_img, null);
			TouchImageView img = (TouchImageView) v.findViewById(R.id.img);
			views.add(v);
			img.setOnDoubleTapListener(new OnDoubleTapListener() {

				@Override
				public boolean onSingleTapConfirmed(MotionEvent e) {
					dismiss();
					return false;
				}

				@Override
				public boolean onDoubleTapEvent(MotionEvent e) {
					return false;
				}

				@Override
				public boolean onDoubleTap(MotionEvent e) {
					return false;
				}
			});
		}
		viewPager.setAdapter(new PagerAdapter() {
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			};

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				try {
					((ExtendedViewPager) container).addView(views.get(position),
							new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				} catch (Exception e) {
				}
				return views.get(position);
			};

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}
		});

		viewPager.setPageMargin((int) activity.getResources().getDimensionPixelOffset(R.dimen.margin_normal));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				txtIndex.setText(activity.getString(R.string.txt_index, arg0 + 1, listImg.size()));
				View view = views.get(arg0);
				final ProgressBar bar = (ProgressBar) view.findViewById(R.id.bar);
				final TouchImageView img = (TouchImageView) view.findViewById(R.id.img);
				ImageLoaderUtil.getInstance().bindImgFitCenter(img, listImg.get(arg0), new OnLoadProgressListener() {

					@Override
					public void onStarted() {
						bar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoading(long total, long current, boolean isDownloading) {
					}

					@Override
					public void onFinished() {
						bar.setVisibility(View.GONE);
					}
				}, true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		viewPager.setCurrentItem(index);
		if (index == 0) {
			View view = views.get(index);
			final ProgressBar bar = (ProgressBar) view.findViewById(R.id.bar);
			final TouchImageView img = (TouchImageView) view.findViewById(R.id.img);
			ImageLoaderUtil.getInstance().bindImgFitCenter(img, listImg.get(index), new OnLoadProgressListener() {

				@Override
				public void onLoading(long total, long current, boolean isDownloading) {
				}

				@Override
				public void onFinished() {
					bar.setVisibility(View.GONE);
				}

				@Override
				public void onStarted() {
					bar.setVisibility(View.VISIBLE);
				}
			}, true);
		}
	}
}
