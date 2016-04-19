package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragment;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.fragment.GuideFragment1;
import com.xinwei.kanfangshenqi.fragment.GuideFragment2;
import com.xinwei.kanfangshenqi.fragment.GuideFragment3;
import com.xinwei.kanfangshenqi.view.AnimPoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class GuideActivity extends BaseFragmentActivity {
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	@ViewInject(R.id.animPoint)
	private AnimPoint animPoint;
	private List<BaseFragment> listFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShowTitle(false);
		addChildView(R.layout.activity_guide);
	}

	@Override
	public void onChildViewLoaded() {
		listFragment = new ArrayList<BaseFragment>();
		listFragment.add(new GuideFragment1());
		listFragment.add(new GuideFragment2());
		listFragment.add(new GuideFragment3());
		viewPager.setAdapter(new PageAdp(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					animPoint.onPageScrollEnd();
				}
				switch(arg0){
				case 0:
					((GuideFragment1)listFragment.get(0)).reload();
					break;
				case 1:
//					((GuideFragment2)listFragment.get(1)).reload();
					((GuideFragment1)listFragment.get(0)).hideView();
					((GuideFragment3)listFragment.get(2)).hideView();
					break;
				case 2:
					((GuideFragment3)listFragment.get(2)).reload();
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				animPoint.onPageScrolled(arg0, arg1, arg2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				animPoint.setSelectPosition(arg0);
			}
		});
		animPoint.setDianCount(3);
		animPoint.setMargin(DensityUtil.dip2px(10));
//		((GuideFragment1)listFragment.get(0)).reload();
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return null;
	}

	class PageAdp extends FragmentPagerAdapter {

		public PageAdp(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
//			Utils.logCN("=================arg0:"+arg0);
//			switch (arg0) {
//			case 0:
//				fragment = new GuideFragment1();
//				// if(page1Done)
//				// fragment.onChildViewLoaded();
//				// else
//				// page1Done = true;
//				break;
//			case 1:
//				fragment = new GuideFragment2();
//				// if(page2Done)
//				// fragment.onChildViewLoaded();
//				// else
//				// page2Done = true;
//				break;
//			case 2:
//				fragment = new GuideFragment3();
//				// if(page3Done)
//				// fragment.onChildViewLoaded();
//				// else
//				// page3Done = true;
//				break;
//			}
			return listFragment.get(arg0);
		}

		@Override
		public int getCount() {
			return 3;
		}

	}
}
