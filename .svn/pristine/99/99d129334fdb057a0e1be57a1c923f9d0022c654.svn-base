package com.xinwei.kanfangshenqi.activity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Recommend;
import com.xinwei.kanfangshenqi.fragment.BuildingDetailInfoByConditionFragment;
import com.xinwei.kanfangshenqi.model.BuildingDetailInfo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RecommendBuildingsActivity extends BaseFragmentActivity {

	@ViewInject(R.id.radioGroup)
	private RadioGroup radioGroup;

	@ViewInject(R.id.radioBtnSynthesize)
	private RadioButton radioBtnSynthesize;
	@ViewInject(R.id.cutLineSynthesize)
	private View cutLineSynthesize;

	@ViewInject(R.id.radioBtnArea)
	private RadioButton radioBtnArea;
	@ViewInject(R.id.cutLineArea)
	private View cutLineArea;

	@ViewInject(R.id.radioBtnPrice)
	private RadioButton radioBtnPrice;
	@ViewInject(R.id.cutLinePrice)
	private View cutLinePrice;

	@ViewInject(R.id.radioBtnFeature)
	private RadioButton radioBtnFeature;
	@ViewInject(R.id.cutLineFeature)
	private View cutLineFeature;

	private BuildingDetailInfoByConditionFragment fragmentSynthesize;
	private BuildingDetailInfoByConditionFragment fragmentArea;
	private BuildingDetailInfoByConditionFragment fragmentPrice;
	private BuildingDetailInfoByConditionFragment fragmentFeature;

	private BuildingDetailInfo buildingDetailInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_recommend_buildings);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt("猜你喜欢");
		buildingDetailInfo = (BuildingDetailInfo) getIntent()
				.getSerializableExtra(BuildingDetailInfo.class.getSimpleName());
		if (buildingDetailInfo == null) {
			finish();
			return;
		}
		onCheckedChangedEvent(null, R.id.radioBtnSynthesize);
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return RecommendBuildingsActivity.class.getSimpleName();
	}

	// 切换相关搜索楼盘信息
	@Event(value = { R.id.radioGroup }, type = OnCheckedChangeListener.class)
	private void onCheckedChangedEvent(RadioGroup group, int checkedId) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		BuildingDetailInfoByConditionFragment fragmentTemp = null;
		showAllFragmentCutLine();
		switch (checkedId) {
		case R.id.radioBtnSynthesize:
			if (fragmentSynthesize == null)
				fragmentSynthesize = new BuildingDetailInfoByConditionFragment(Recommend.SYNTHESIZE,
						buildingDetailInfo);
			if (!fragmentSynthesize.isAdded())
				ft.add(R.id.frameLayout, fragmentSynthesize);
			fragmentTemp = fragmentSynthesize;
			cutLineSynthesize.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnArea:
			if (fragmentArea == null)
				fragmentArea = new BuildingDetailInfoByConditionFragment(Recommend.AREA, buildingDetailInfo);
			if (!fragmentArea.isAdded())
				ft.add(R.id.frameLayout, fragmentArea);
			fragmentTemp = fragmentArea;
			cutLineArea.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnPrice:
			if (fragmentPrice == null)
				fragmentPrice = new BuildingDetailInfoByConditionFragment(Recommend.PRICE, buildingDetailInfo);
			if (!fragmentPrice.isAdded())
				ft.add(R.id.frameLayout, fragmentPrice);
			fragmentTemp = fragmentPrice;
			cutLinePrice.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnFeature:
			if (fragmentFeature == null)
				fragmentFeature = new BuildingDetailInfoByConditionFragment(Recommend.FEATURE, buildingDetailInfo);
			if (!fragmentFeature.isAdded())
				ft.add(R.id.frameLayout, fragmentFeature);
			fragmentTemp = fragmentFeature;
			cutLineFeature.setVisibility(View.INVISIBLE);
			break;
		}
		hideAllFragment(ft);
		ft.show(fragmentTemp);
		ft.commit();
	}

	// 显示搜索楼盘tab下方分割线
	private void showAllFragmentCutLine() {
		cutLineSynthesize.setVisibility(View.VISIBLE);
		cutLineArea.setVisibility(View.VISIBLE);
		cutLinePrice.setVisibility(View.VISIBLE);
		cutLineFeature.setVisibility(View.VISIBLE);
	}

	private void hideAllFragment(FragmentTransaction ft) {
		if (fragmentSynthesize != null && fragmentSynthesize.isAdded())
			ft.hide(fragmentSynthesize);
		if (fragmentArea != null && fragmentArea.isAdded())
			ft.hide(fragmentArea);
		if (fragmentPrice != null && fragmentPrice.isAdded())
			ft.hide(fragmentPrice);
		if (fragmentFeature != null && fragmentFeature.isAdded())
			ft.hide(fragmentFeature);
	}

}
