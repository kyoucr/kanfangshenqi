package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.EncyclopediaFragment;
import com.xinwei.kanfangshenqi.model.Encyclopedia;
import com.xinwei.kanfangshenqi.model.EncyclopediaTitle;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.view.TransLateView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
/**
 *******************
 * 百科
 * @author cn
 *
 *******************
 */
public class EncyclopediaActivity extends BaseFragmentActivity {
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	@ViewInject(R.id.lltNavigation)
	private LinearLayout lltNavigation;
	@ViewInject(R.id.transLateView)
	private TransLateView transLateView;
	private Encyclopedia encyclopedia;
	private List<Button> btnList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLeftTitle(getString(R.string.tab_bar_ic_faxian));
		addChildView(R.layout.activity_encyclopedia);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.encyclopedia);
		isShowTitleCutline(false);
		isShowContent(false);
		onReloadData();
	}

	@Override
	public void onReloadData() {
		showBar();
		HttpRequest.get(this, Const.URL_ENCYCLOPEDIACLASSES, getRequestTag(), null, null,
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						isShowContent(true);
						encyclopedia = new Gson().fromJson(responseResult, Encyclopedia.class);
						initNavigation();
						EncyclopediaFragmentAdp adp = new EncyclopediaFragmentAdp(getSupportFragmentManager());
						viewPager.setAdapter(adp);
						viewPager.setOnPageChangeListener(new OnPageChangeListener() {

							@Override
							public void onPageSelected(int arg0) {
								for (int i = 0; i < btnList.size(); i++) {
									if (i == arg0)
										btnList.get(i).setTextColor(getResources().getColor(R.color.orange));
									else
										btnList.get(i).setTextColor(getResources().getColor(R.color.light_gray));
								}
							}

							@Override
							public void onPageScrolled(int arg0, float arg1, int arg2) {
								transLateView.setXx((float) arg0 * transLateView.getW() + transLateView.getW() * arg1);
							}

							@Override
							public void onPageScrollStateChanged(int arg0) {
							}
						});
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						isShowError(true);
					}

					@Override
					public void onError(String url, String responseResult) {
						isShowError(true);
					}
				});
	}
	//初始化导航栏
	private void initNavigation() {
		btnList = new ArrayList<Button>();
		for (int i = 0;i<encyclopedia.getFirstClassList().size();i++){
			EncyclopediaTitle title = encyclopedia.getFirstClassList().get(i);
			Button btn = new Button(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
			params.weight = 1;
			btn.setLayoutParams(params);
			btn.setText(title.getClassName());
			btn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
			btn.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
			if (i == 0) {
				btn.setTextColor(getResources().getColor(R.color.orange));
			} else {
				btn.setTextColor(getResources().getColor(R.color.light_gray));
			}
			final int currentItem = i;
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					viewPager.setCurrentItem(currentItem);
				}
			});
			lltNavigation.addView(btn);
			btnList.add(btn);
		}
	}

	class EncyclopediaFragmentAdp extends FragmentPagerAdapter {

		public EncyclopediaFragmentAdp(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			if(arg0 == 0){
				return new EncyclopediaFragment(activity,encyclopedia.getSecondClassList());
			}else{
				return new EncyclopediaFragment(activity,encyclopedia.getFirstClassList().get(arg0).getClassId());
			}
		}

		@Override
		public int getCount() {
			if (encyclopedia == null || encyclopedia.getFirstClassList() == null)
				return 0;
			transLateView.setInitX(encyclopedia.getFirstClassList().size());
			transLateView.setPadding(getResources().getDimension(R.dimen.margin_normal));
			return encyclopedia.getFirstClassList().size();
		}

	}

	@Override
	public String getRequestTag() {
		return EncyclopediaActivity.class.getSimpleName();
	}
}