package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.fragment.PublishedFragment;
import com.xinwei.kanfangshenqi.fragment.UnPublishFragment;
import com.xinwei.kanfangshenqi.view.TransLateView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
/**
 ********************
 * 发表有礼
 * @author cn
 ********************
 */
public class PublishGiftActivity extends BaseFragmentActivity {
	@ViewInject(R.id.transLateView)
	private TransLateView transLateView;
	@ViewInject(R.id.radioGroup)
	private RadioGroup radioGroup;
	@ViewInject(R.id.radioUnPublish)
	private RadioButton radioBtnUnPublish;
	@ViewInject(R.id.radioPublished)
	private RadioButton radioBtnPublished;
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	private UnPublishFragment unPublishFragment;
	private PublishedFragment publishedFragment;
	private static final int UNPUBLISH_PAGE = 0;
	private static final int PUBLISHED_PAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_publish_gift);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.title_publish_gift);
		setLeftTitle(R.string.user);
		isShowTitleCutline(false);
		//关闭预加载，默认一次只加载一个Fragment
		viewPager.setOffscreenPageLimit(0);
		viewPager.setAdapter(new PublishFragmentAdp(
				getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case UNPUBLISH_PAGE:
					radioBtnUnPublish.setChecked(true);
					break;
				case PUBLISHED_PAGE:
					radioBtnPublished.setChecked(true);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				transLateView.setXx((float) arg0 * transLateView.getW()
						+ transLateView.getW() * arg1);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		transLateView.setInitX((float) radioBtnUnPublish.getWidth());
		transLateView.setPadding(getResources().getDimension(R.dimen.margin_normal));
		radioBtnUnPublish.setChecked(true);
	}
	@Event(value=R.id.radioGroup,type=OnCheckedChangeListener.class)
	private void onCheckChangedEvent(RadioGroup group, int checkedId){
		switch (checkedId) {
		case R.id.radioUnPublish:
			viewPager.setCurrentItem(UNPUBLISH_PAGE);
			break;
		case R.id.radioPublished:
			viewPager.setCurrentItem(PUBLISHED_PAGE);
			break;
		}
	}
	@Override
	public void onReloadData() {

	}

	@Override
	public String getRequestTag() {
		return PublishGiftActivity.class.getSimpleName();
	}

	class PublishFragmentAdp extends FragmentPagerAdapter {

		public PublishFragmentAdp(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			BaseFragment fragment = null;
			switch (arg0) {
			case UNPUBLISH_PAGE:
				if (unPublishFragment == null)
					unPublishFragment = new UnPublishFragment();
				fragment = unPublishFragment;
				break;
			case PUBLISHED_PAGE:
				if (publishedFragment == null)
					publishedFragment = new PublishedFragment();
				fragment = publishedFragment;
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}


}