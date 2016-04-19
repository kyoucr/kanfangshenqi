package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.melnykov.fab.ScrollDirectionListener;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinwei.kanfangshenqi.BaseFragmentActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.BuildingDetailInfoSeeHouseCircleAdp;
import com.xinwei.kanfangshenqi.adapter.HouseTypeAdp;
import com.xinwei.kanfangshenqi.adapter.SurroundingAdp;
import com.xinwei.kanfangshenqi.common.BuildingDetailInfoView;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.BuildingDetailInfoFragment;
import com.xinwei.kanfangshenqi.fragment.JiHuaFragment;
import com.xinwei.kanfangshenqi.model.BuildingDetail.Building;
import com.xinwei.kanfangshenqi.model.BuildingDetailInfo;
import com.xinwei.kanfangshenqi.model.DataList;
import com.xinwei.kanfangshenqi.model.Desc;
import com.xinwei.kanfangshenqi.model.FeatureList;
import com.xinwei.kanfangshenqi.model.HouseType;
import com.xinwei.kanfangshenqi.model.Interest;
import com.xinwei.kanfangshenqi.model.InterestCtrlResult;
import com.xinwei.kanfangshenqi.model.UnPlan;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.response.GetCodeResponse;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.ImageOptions;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.SystemBarTintManager;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CornerRadiusButton;
import com.xinwei.kanfangshenqi.view.CornerRadiusTextView;
import com.xinwei.kanfangshenqi.view.ImgDetailPop;
import com.xinwei.kanfangshenqi.view.ListViewInScroll;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BuildingDetailInfoActivity extends BaseFragmentActivity {
	@ViewInject(R.id.rltAllTop)
	private RelativeLayout rltAllTop;
	@ViewInject(R.id.rltBottomDesc)
	private RelativeLayout rltBottomDesc;
	@ViewInject(R.id.rltRedEnvelope)
	private RelativeLayout rltRedEnvelope;
	@ViewInject(R.id.txtSubTitle)
	private TextView txtSubTitle;
	@ViewInject(R.id.imgBuilding)
	private ImageView imgBuilding;
	@ViewInject(R.id.viewStatus)
	private View viewStatus;
	@ViewInject(R.id.rltFeature)
	private RelativeLayout rltFeature;
	@ViewInject(R.id.txtFeature1)
	private CornerRadiusTextView txtFeature1;
	@ViewInject(R.id.imgFeature1)
	private ImageView imgFeature1;
	@ViewInject(R.id.txtFeature2)
	private CornerRadiusTextView txtFeature2;
	@ViewInject(R.id.imgFeature2)
	private ImageView imgFeature2;
	@ViewInject(R.id.txtFeature3)
	private CornerRadiusTextView txtFeature3;
	@ViewInject(R.id.imgFeature3)
	private ImageView imgFeature3;
	@ViewInject(R.id.txtPrice)
	private TextView txtPrice;
	@ViewInject(R.id.txtUnit)
	private TextView txtUnit;
	@ViewInject(R.id.txtAddr)
	private TextView txtAddr;
	@ViewInject(R.id.imgRedEnvelope)
	private ImageView imgRedEnvelope;

	@ViewInject(R.id.lltHouseStyle)
	private LinearLayout lltHouseStyle;
	@ViewInject(R.id.gridViewHouseStyle)
	private GridView gridViewHouseStyle;
	// @ViewInject(R.id.lViHouseType)
	// private HorizontalListView lViHouseType;
	@ViewInject(R.id.txtEmptyHouseStyle)
	private TextView txtEmptyHouseStyle;

	@ViewInject(R.id.btnInterest)
	private CornerRadiusTextView btnInterest;

	@ViewInject(R.id.lViSurrounding)
	private ListViewInScroll lViSurrounding;
	@ViewInject(R.id.txtEmptySurrounding)
	private TextView txtEmptySurrounding;
	@ViewInject(R.id.txtSurroundingMore)
	private TextView txtSurroundingMore;
	@ViewInject(R.id.rltSurroundingMore)
	private RelativeLayout rltSurroundingMore;
	@ViewInject(R.id.lViBuildingInfo)

	private ListViewInScroll lViBuildingInfo;
	@ViewInject(R.id.txtEmptyBuildingInfo)
	private TextView txtEmptyBuildingInfo;
	@ViewInject(R.id.txtBuildingInfoMore)
	private TextView txtBuildingInfoMore;
	@ViewInject(R.id.rltBuildingInfoMore)
	private RelativeLayout rltBuildingInfoMore;

	@ViewInject(R.id.lViSeeHouseCircle)
	private ListViewInScroll lViSeeHouseCircle;
	@ViewInject(R.id.txtEmptySeeHouseCircle)
	private TextView txtEmptySeeHouseCircle;
	@ViewInject(R.id.rltSeeHouseCircelMore)
	private RelativeLayout rltSeeHouseCircelMore;

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

	@ViewInject(R.id.frameLayout)
	private FrameLayout frameLayout;

	@ViewInject(R.id.rltBuildingInfoDetailMore)
	private RelativeLayout rltBuildingInfoDetailMore;

	@ViewInject(R.id.scrollView)
	private ObservableScrollView scrollView;

	@ViewInject(R.id.btnPlan)
	private FloatingActionButton btnPlan;

	private DataList dataListPre;
	private BuildingDetailInfo buildingDetailInfo;
	private BuildingDetailInfoSeeHouseCircleAdp adpSeeHouseCircle;
	private HouseTypeAdp adpHouseType;
	private SurroundingAdp adpSurrounding;
	private SurroundingAdp adpBuildingInfo;

	private BuildingDetailInfoFragment fragmentSynthesize;
	private BuildingDetailInfoFragment fragmentArea;
	private BuildingDetailInfoFragment fragmentPrice;
	private BuildingDetailInfoFragment fragmentFeature;

	private int heightShort = 150;// 未点击展开更多时的控件高度

	private SystemBarTintManager tintManager;
	private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
	private static final int TRANSLATE_DURATION_MILLIS = 200;
	private PopupWindow popRedFromTop;
	private PopupWindow popRedFromCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_building_detail_info);
	}

	@Override
	public void onChildViewLoaded() {
		Intent intent = getIntent();
		dataListPre = (DataList) intent.getSerializableExtra(DataList.class.getSimpleName());
		if (dataListPre == null) {
			finish();
			return;
		}
		EventBus.getDefault().register(this);
		initView();
		requestBuildingInfo();
	}

	private void requestBuildingInfo() {
		showBar();
		HttpRequest.get(this, Const.URL_BUILDING_DETAIL_INFO + dataListPre.getBuildingId(), getRequestTag(), null,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						buildingDetailInfo = new Gson().fromJson(responseResult, BuildingDetailInfo.class);
						initView();
						loadViewAfterRequest();
						isShowContent(true);
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

	private void initView() {
		isShowTitle(false);
		// if (Utils.setTransparentStatusBar(this)) {
		// viewStatus.getLayoutParams().height = Utils.getStatusBarHeight();
		// viewStatus.setLayoutParams(viewStatus.getLayoutParams());
		// // 创建状态栏的管理实例
		// tintManager = new SystemBarTintManager(activity);
		// // 激活状态栏设置
		// tintManager.setStatusBarTintEnabled(true);
		// // 激活导航栏设置
		// tintManager.setNavigationBarTintEnabled(true);
		// // 设置一个颜色给系统栏
		// tintManager.setTintColor(getResources().getColor(R.color.building_title_color));
		// tintManager.setStatusBarTintColor(getResources().getColor(R.color.building_title_color));
		// RelativeLayout.LayoutParams params =
		// (android.widget.RelativeLayout.LayoutParams) rltFeature
		// .getLayoutParams();
		// params.topMargin =
		// getResources().getDimensionPixelSize(R.dimen.title_height)
		// + viewStatus.getLayoutParams().height;
		// rltFeature.setLayoutParams(params);
		// // Utils.setMiuiStatusBarDarkMode(this,true);
		// // Utils.setMeizuStatusBarDarkIcon(this,true);
		// }
		imgBuilding.getLayoutParams().height = (int) (DensityUtil.getScreenWidth() * 0.9);
		imgBuilding.setLayoutParams(imgBuilding.getLayoutParams());
		List<FeatureList> featureList = dataListPre.getFeatureList();
		for (int i = 0; i < featureList.size(); i++) {
			FeatureList feature = featureList.get(i);
			switch (i) {
			case 0:
				txtFeature1.setVisibility(View.VISIBLE);
				TextViewWriterUtil.writeValue(txtFeature1, feature.getFeatureName());
				imgFeature1.setVisibility(View.VISIBLE);
				break;
			case 1:
				txtFeature2.setVisibility(View.VISIBLE);
				TextViewWriterUtil.writeValue(txtFeature2, feature.getFeatureName());
				imgFeature2.setVisibility(View.VISIBLE);
				break;
			case 2:
				txtFeature3.setVisibility(View.VISIBLE);
				TextViewWriterUtil.writeValue(txtFeature3, feature.getFeatureName());
				imgFeature3.setVisibility(View.VISIBLE);
				break;
			}
		}
		if (ValidatorUtil.isValidString(dataListPre.getAveragePrice()))
			TextViewWriterUtil.writeValue(txtPrice, dataListPre.getAveragePrice());
		else
			txtUnit.setVisibility(View.INVISIBLE);
		TextViewWriterUtil.writeValue(txtSubTitle, dataListPre.getBuildingName());
		ImageLoaderUtil.getInstance().bindImgFixXY(imgBuilding, dataListPre.getBanner());
		btnInterest.setStroke(1, getResources().getColor(R.color.orange));
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (buildingDetailInfo != null)
			ImageLoaderUtil.getInstance().bindImgFitXYWithoutTagAndImageOptionsCache(imgBuilding, buildingDetailInfo.getBanner());
	}

	private void loadViewAfterRequest() {
		if (buildingDetailInfo == null)
			return;
		TextViewWriterUtil.writeValue(txtAddr, buildingDetailInfo.getAddress());
		// 初始化户型图
		final List<HouseType> houseTypes = buildingDetailInfo.getUnitMaps();
		if (ValidatorUtil.isValidList(houseTypes)) {
			adpHouseType = new HouseTypeAdp(this, houseTypes);
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) gridViewHouseStyle.getLayoutParams();
			params.width = getResources().getDimensionPixelSize(R.dimen.item_size_house_type) * houseTypes.size()
					+ getResources().getDimensionPixelSize(R.dimen.margin_big) * houseTypes.size();
			gridViewHouseStyle.setNumColumns(houseTypes.size());
			gridViewHouseStyle.setLayoutParams(params);
			gridViewHouseStyle.setAdapter(adpHouseType);
			txtEmptyHouseStyle.setVisibility(View.GONE);
		} else {
			txtEmptyHouseStyle.setVisibility(View.VISIBLE);
		}
		// 初始化看房圈
		List<Building> buildingComments = buildingDetailInfo.getBuildingComments();
		if (ValidatorUtil.isValidList(buildingComments)) {
			adpSeeHouseCircle = new BuildingDetailInfoSeeHouseCircleAdp(this, buildingComments);
			lViSeeHouseCircle.setAdapter(adpSeeHouseCircle);
			rltSeeHouseCircelMore.setVisibility(View.VISIBLE);
			txtEmptySeeHouseCircle.setVisibility(View.GONE);
		} else {
			rltSeeHouseCircelMore.setVisibility(View.GONE);
			txtEmptySeeHouseCircle.setVisibility(View.VISIBLE);
			txtEmptySeeHouseCircle.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					toBuildingComment();
				}
			});
		}
		// 初始化楼盘详情
		List<Desc> buildingInfos = buildingDetailInfo.getBuildingInfo();
		if (ValidatorUtil.isValidList(buildingInfos)) {
			lViBuildingInfo.setAuto(false);
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) lViBuildingInfo
					.getLayoutParams();
			params.height = DensityUtil.dip2px(heightShort);
			lViBuildingInfo.setAuto(false);
			lViBuildingInfo.setLayoutParams(params);
			// if (buildingInfos.size() > 5) {
			adpBuildingInfo = new SurroundingAdp(this, buildingInfos, R.layout.adp_surrouding, false);
			lViBuildingInfo.setAdapter(adpBuildingInfo);
			rltBuildingInfoMore.setVisibility(View.VISIBLE);
			rltBuildingInfoMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) lViBuildingInfo
							.getLayoutParams();
					if (adpBuildingInfo.isShowAll()) {
						lViBuildingInfo.setAuto(false);
						TextViewWriterUtil.writeValue(txtBuildingInfoMore, getString(R.string.show_more));
						adpBuildingInfo.setShowAll(false);
						params.height = DensityUtil.dip2px(heightShort);
					} else {
						TextViewWriterUtil.writeValue(txtBuildingInfoMore, getString(R.string.hide_more));
						adpBuildingInfo.setShowAll(true);
						lViBuildingInfo.setAuto(true);
						params.height = LayoutParams.WRAP_CONTENT;
					}
					lViBuildingInfo.setLayoutParams(params);
				}
			});
			// } else {
			// adpBuildingInfo = new SurroundingAdp(this, buildingInfos,
			// R.layout.adp_surrouding, true);
			// lViBuildingInfo.setAdapter(adpBuildingInfo);
			// rltBuildingInfoMore.setVisibility(View.GONE);
			// }
			txtEmptyBuildingInfo.setVisibility(View.GONE);
		} else {
			txtEmptyBuildingInfo.setVisibility(View.VISIBLE);
		}

		// 初始化周边配套
		List<Desc> surroundings = buildingDetailInfo.getSurrounding();
		if (ValidatorUtil.isValidList(surroundings)) {
			lViSurrounding.setAuto(false);
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) lViSurrounding
					.getLayoutParams();
			params.height = DensityUtil.dip2px(heightShort);
			lViSurrounding.setAuto(false);
			lViSurrounding.setLayoutParams(params);
			// if (buildingInfos.size() > 5) {
			adpSurrounding = new SurroundingAdp(this, surroundings, R.layout.adp_surrouding, false);
			lViSurrounding.setAdapter(adpSurrounding);
			rltSurroundingMore.setVisibility(View.VISIBLE);
			rltSurroundingMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) lViSurrounding
							.getLayoutParams();
					if (adpSurrounding.isShowAll()) {
						lViSurrounding.setAuto(false);
						TextViewWriterUtil.writeValue(txtSurroundingMore, getString(R.string.show_more));
						adpSurrounding.setShowAll(false);
						params.height = DensityUtil.dip2px(heightShort);
					} else {
						TextViewWriterUtil.writeValue(txtSurroundingMore, getString(R.string.hide_more));
						adpSurrounding.setShowAll(true);
						lViSurrounding.setAuto(true);
						params.height = LayoutParams.WRAP_CONTENT;
					}
					lViSurrounding.setLayoutParams(params);
				}
			});
			// } else {
			// adpSurrounding = new SurroundingAdp(this, buildingInfos,
			// R.layout.adp_surrouding, true);
			// lViSurrounding.setAdapter(adpSurrounding);
			// rltSurroundingMore.setVisibility(View.GONE);
			// }
			txtEmptySurrounding.setVisibility(View.GONE);
		} else {
			txtEmptySurrounding.setVisibility(View.VISIBLE);
		}
		// 初始化同类搜索
		if (!ValidatorUtil.isValidList(buildingDetailInfo.getSynthesize())
				&& !ValidatorUtil.isValidList(buildingDetailInfo.getArea())
				&& !ValidatorUtil.isValidList(buildingDetailInfo.getPrice())
				&& !ValidatorUtil.isValidList(buildingDetailInfo.getFeature()))
			rltBuildingInfoDetailMore.setVisibility(View.GONE);
		else
			rltBuildingInfoDetailMore.setVisibility(View.VISIBLE);
		// 默认选中综合楼盘
		onCheckedChangedEvent(null, R.id.radioBtnSynthesize);
		// 初始化添加计划按钮
		btnPlan.attachToScrollView(scrollView, new ScrollDirectionListener() {
			@Override
			public void onScrollUp() {
				// if(tintManager!=null){
				// tintManager.setTintColor(getResources().getColor(android.R.color.transparent));
				// tintManager.setStatusBarTintColor(getResources().getColor(android.R.color.transparent));
				// }
				ViewPropertyAnimator.animate(rltAllTop).setInterpolator(mInterpolator)
						.setDuration(TRANSLATE_DURATION_MILLIS).translationY(-rltAllTop.getHeight());
			}

			@Override
			public void onScrollDown() {
				// if(tintManager!=null){
				// tintManager.setTintColor(getResources().getColor(R.color.building_title_color));
				// tintManager.setStatusBarTintColor(getResources().getColor(R.color.building_title_color));
				// }
				ViewPropertyAnimator.animate(rltAllTop).setInterpolator(mInterpolator)
						.setDuration(TRANSLATE_DURATION_MILLIS).translationY(0);
			}
		});
		// 初始化添加计划按钮
		if (Const.YES.equals(buildingDetailInfo.getIsPlan()))
			btnPlan.setBackgroundCompat(getResources().getDrawable(R.drawable.btn_planned));
		else
			btnPlan.setBackgroundCompat(getResources().getDrawable(R.drawable.btn_plan));
		btnPlan.setVisibility(View.VISIBLE);
		// 初始化添加关注按钮
		if (Const.YES.equals(buildingDetailInfo.getIsFollow()))
			btnInterest.setText(getString(R.string.txt_cancel_interest));
		else
			btnInterest.setText(getString(R.string.txt_add_interest));
		btnInterest.setVisibility(View.VISIBLE);
		btnInterest.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (btnInterest.getWidth() > 0) {
					btnInterest.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					closeBar();
					judgeRed();
				}
			}
		});
	}

	/*
	 * 判断红包是否显示
	 */
	private void judgeRed() {
		if (ValidatorUtil.isValidString(buildingDetailInfo.getScancodePrice())) {
			try {
				if (Double.parseDouble(buildingDetailInfo.getScancodePrice()) > 0) {
					showPopRedFromTop();
				}
			} catch (NumberFormatException e) {
			}
		}
	}

	private ImgDetailPop popImg;

	public void showImgPop(int position) {
		List<String> listImg = new ArrayList<String>();
		for (int i = 0; i < buildingDetailInfo.getUnitMaps().size(); i++) {
			listImg.add(buildingDetailInfo.getUnitMaps().get(i).getImgPath());
		}
		popImg = new ImgDetailPop(activity, listImg, position);
		popImg.showAtLocation(rltAllTop, Gravity.NO_GRAVITY, 0, 0);
	}

	// 显示红包掉落pop
	private void showPopRedFromTop() {
		View vRed = LayoutInflater.from(this).inflate(R.layout.pop_building_detail_info_red, null);
		popRedFromTop = new PopupWindow(vRed, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// popRed.setContentView(vRed);
		vRed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popRedFromTop != null && popRedFromTop.isShowing())
					popRedFromTop.dismiss();
			}
		});
		final ImageView imgRed = (ImageView) vRed.findViewById(R.id.imgRed);
		imgRed.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				if (imgRed.getHeight() > 0) {
					imgRed.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					// AnimatorSet set = new AnimatorSet();
					ObjectAnimator animY = ObjectAnimator.ofFloat(imgRed, "Y", -imgRed.getHeight(), imgRed.getY())
							.setDuration(1500);
					animY.setInterpolator(new AccelerateInterpolator());
					animY.start();
					// ObjectAnimator animAlpha = ObjectAnimator.ofFloat(imgRed,
					// "Alpha", 0, 1).setDuration(1500);
					// set.playTogether(animY,animAlpha);
					// set.start();
				}
			}
		});
		popRedFromTop.showAtLocation(rltAllTop, Gravity.NO_GRAVITY, 0, 0);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (popRedFromTop != null && popRedFromTop.isShowing())
					popRedFromTop.dismiss();
			}
		}, 4500);
		popRedFromTop.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				showRedEnvelopeIcon();
			}
		});
	}

	// 显示红包掉落pop
	private void showPopRedFromCenter() {
		View vRed = LayoutInflater.from(this).inflate(R.layout.pop_building_detail_info_red, null);
		popRedFromCenter = new PopupWindow(vRed, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// popRed.setContentView(vRed);
		popRedFromCenter.showAtLocation(rltAllTop, Gravity.NO_GRAVITY, 0, 0);
		vRed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popRedFromCenter != null && popRedFromCenter.isShowing())
					popRedFromCenter.dismiss();
			}
		});
		final ImageView imgRed = (ImageView) vRed.findViewById(R.id.imgRed);
		imgRed.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				if (imgRed.getHeight() > 0) {
					imgRed.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					AnimatorSet set = new AnimatorSet();
					ObjectAnimator animAlpha = ObjectAnimator.ofFloat(imgRed, "Alpha", 0, 1).setDuration(1000);
					ObjectAnimator animScaleX = ObjectAnimator.ofFloat(imgRed, "ScaleX", 0, 1).setDuration(1000);
					ObjectAnimator animScaleY = ObjectAnimator.ofFloat(imgRed, "ScaleY", 0, 1).setDuration(1000);
					set.playTogether(animAlpha, animScaleX, animScaleY);
					set.start();
				}
			}
		});
	}

	// 切换相关搜索楼盘信息
	@Event(value = { R.id.radioGroup }, type = OnCheckedChangeListener.class)
	private void onCheckedChangedEvent(RadioGroup group, int checkedId) {
		if (buildingDetailInfo == null)
			return;
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		BuildingDetailInfoFragment fragmentTemp = null;
		showAllFragmentCutLine();
		switch (checkedId) {
		case R.id.radioBtnSynthesize:
			if (fragmentSynthesize == null)
				fragmentSynthesize = new BuildingDetailInfoFragment(buildingDetailInfo.getSynthesize());
			if (!fragmentSynthesize.isAdded())
				ft.add(R.id.frameLayout, fragmentSynthesize);
			fragmentTemp = fragmentSynthesize;
			cutLineSynthesize.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnArea:
			if (fragmentArea == null)
				fragmentArea = new BuildingDetailInfoFragment(buildingDetailInfo.getArea());
			if (!fragmentArea.isAdded())
				ft.add(R.id.frameLayout, fragmentArea);
			fragmentTemp = fragmentArea;
			cutLineArea.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnPrice:
			if (fragmentPrice == null)
				fragmentPrice = new BuildingDetailInfoFragment(buildingDetailInfo.getPrice());
			if (!fragmentPrice.isAdded())
				ft.add(R.id.frameLayout, fragmentPrice);
			fragmentTemp = fragmentPrice;
			cutLinePrice.setVisibility(View.INVISIBLE);
			break;
		case R.id.radioBtnFeature:
			if (fragmentFeature == null)
				fragmentFeature = new BuildingDetailInfoFragment(buildingDetailInfo.getFeature());
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

	// 显示红包图标
	private void showRedEnvelopeIcon() {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rltRedEnvelope.getLayoutParams();
		params.height = rltBottomDesc.getHeight() + imgRedEnvelope.getHeight() + DensityUtil.dip2px(16);
		rltRedEnvelope.setLayoutParams(params);
		ObjectAnimator anim = ObjectAnimator.ofFloat(imgRedEnvelope, "Alpha", 0, 1).setDuration(500);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				rltRedEnvelope.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
				rltRedEnvelope.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void onReloadData() {
		requestBuildingInfo();
	}

	@Override
	public String getRequestTag() {
		return BuildingDetailInfoActivity.class.getSimpleName();
	}

	@Event({ R.id.rltSubLeft, R.id.btnPlan, R.id.btnPhone, R.id.btnInterest, R.id.rltSeeHouseCircelMore,
			R.id.rltBuildingInfoDetailMore, R.id.imgRedEnvelope })
	private void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.rltSubLeft:
			finish();
			break;
		case R.id.btnPlan:
			if (isLogin()) {
				if (!Const.YES.equals(buildingDetailInfo.getIsPlan()))
					addPlan();
				else
					ToastUtil.show(getString(R.string.hint_add_seehouse_plan_already));
			}
			break;
		case R.id.btnPhone:
			makeCall();
			break;
		case R.id.btnInterest:
			if (isLogin()) {
				if (Const.YES.equals(buildingDetailInfo.getIsFollow()))
					cancelInterest();
				else
					addInterest();
			}
			break;
		case R.id.rltSeeHouseCircelMore:
			toBuildingComment();
			break;
		case R.id.rltBuildingInfoDetailMore:
			Bundle data = new Bundle();
			data = new Bundle();
			data.putSerializable(BuildingDetailInfo.class.getSimpleName(), buildingDetailInfo);
			Utils.moveTo(activity, RecommendBuildingsActivity.class, false, data);
			break;
		case R.id.imgRedEnvelope:
			showPopRedFromCenter();
			break;
		}
	}

	private void toBuildingComment() {

		if (isLogin()) {
			Interest interest = new Interest();
			interest.setBanner(buildingDetailInfo.getBanner());
			interest.setBuildingId(buildingDetailInfo.getBuildingId());
			interest.setBuildingName(buildingDetailInfo.getBuildingName());
			Bundle data = new Bundle();
			data.putSerializable(Interest.class.getSimpleName(), interest);
			data.putString(Const.WEB_LEFT_TITLE_KEY, "楼盘详情");
//			data.putString(Const.WEB_TITLE_KEY,buildingDetailInfo.getBuildingName());
			Utils.moveTo(activity, BuildingCommentActivity.class, false, data);
		}
	}

	private void makeCall() {
		if (ValidatorUtil.isValidString(buildingDetailInfo.getBuildingPhone()))
			getAlert().showCustom(null, "呼叫" + buildingDetailInfo.getBuildingPhone(), null, null,
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(Intent.ACTION_CALL,
									Uri.parse("tel://" + buildingDetailInfo.getBuildingPhone()));
							startActivity(intent);
						}
					});
		else
			ToastUtil.show(getString(R.string.hint_no_phone_building));
	}

	// 添加计划
	private void addPlan() {
		showBar();
		setBarCancelable(false);
		Map<String, String> paramsContent = new HashMap<String, String>();
		paramsContent.put(Const.PARAM_BUILDING_ID, buildingDetailInfo.getBuildingId());
		HttpRequest.post(this, Const.URL_ADD_PLAN, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						JiHuaFragment.isNullData = false;
						PreferenceUtils.getInstance().addUnappointplancount();
						UnPlan unPlan = new UnPlan();
						unPlan.setBuildingId(buildingDetailInfo.getBuildingId());
						EventBus.getDefault().post(unPlan);
						ToastUtil.show(getString(R.string.hint_add_seehouse_plan_success));
						buildingDetailInfo.setIsPlan(Const.YES);
						btnPlan.setBackgroundCompat(getResources().getDrawable(R.drawable.btn_planned));
						closeBar();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						GetCodeResponse result = new Gson().fromJson(responseResult, GetCodeResponse.class);
						if (result.getMsg() != null && result.getMsg().contains("已经加入")) {
							buildingDetailInfo.setIsPlan(Const.YES);
							btnPlan.setBackgroundCompat(getResources().getDrawable(R.drawable.btn_planned));
						}
						closeBar();
					}
				}, false);
	}

	// 添加关注
	private void addInterest() {
		showBar();
		setBarCancelable(false);
		Map<String, String> paramsContent = new HashMap<String, String>();
		paramsContent.put(Const.PARAM_BUILDING_ID, buildingDetailInfo.getBuildingId());
		HttpRequest.post(this, Const.URL_ADD_INTEREST, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						InterestCtrlResult result = new Gson().fromJson(responseResult, InterestCtrlResult.class);
						buildingDetailInfo.setFollowId(result.getFollowId());
						ToastUtil.show(getString(R.string.hint_add_interest_success));
						buildingDetailInfo.setIsFollow(Const.YES);
						btnInterest.setText(getString(R.string.txt_cancel_interest));
						closeBar();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						InterestCtrlResult result = new Gson().fromJson(responseResult, InterestCtrlResult.class);
						if (result.getMsg() != null && result.getMsg().contains("已")) {
							buildingDetailInfo.setIsFollow(Const.YES);
							buildingDetailInfo.setFollowId(result.getFollowId());
							btnInterest.setText(getString(R.string.txt_cancel_interest));
						}
						closeBar();
					}
				}, false);
	}

	private void cancelInterest() {
		showBar();
		HttpRequest.post(this, Const.URL_CANCEL_INTEREST + buildingDetailInfo.getFollowId(), getRequestTag(), null,
				Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						ToastUtil.show(getString(R.string.hint_cancel_interest_success));
						btnInterest.setText(getString(R.string.txt_add_interest));
						buildingDetailInfo.setIsFollow(Const.NO);
						closeBar();
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						closeBar();
					}
				});
	}

	// 禁止listview滑动
	@Event(value = { R.id.lViBuildingInfo, R.id.lViSurrounding }, type = OnTouchListener.class)
	private boolean onViewTouchEvent(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.lViBuildingInfo:
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				return true;
			}
			break;
		case R.id.lViSurrounding:
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				return true;
			}
			break;
		}
		return false;
	}

	@Event(value = { R.id.lViSeeHouseCircle }, type = OnItemClickListener.class)
	private void onItemClickEvent(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		toBuildingComment();
//		try {
//			if (isLogin()) {
//				Building Building = (Building) arg0.getAdapter().getItem(arg2);
//				Bundle data = new Bundle();
//				data.putString(AskDetailNewActivity.class.getSimpleName(), Building.getCommentId());
//				data.putBoolean(Const.URL_BUILDING_COMMENT_DETAIL, true);
//				data.putString(Const.WEB_LEFT_TITLE_KEY, buildingDetailInfo.getBuildingName());
//				Utils.moveTo(activity, AskDetailNewActivity.class, false, data);
//			}
//		} catch (Exception e) {
//		}
	}

	// 楼盘列表适配器加载时调用，动态计算应显示高度
	@Subscribe
	public void onFragmentLoadedEvent(BuildingDetailInfoView vuildDetailInfoView) {
		LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) frameLayout.getLayoutParams();
		params.height = vuildDetailInfoView.getHeightFragment() * 3 + DensityUtil.dip2px(1);
		frameLayout.setLayoutParams(params);
	}

	@Override
	public void onBackPressed() {
		if (popRedFromTop != null && popRedFromTop.isShowing())
			popRedFromTop.dismiss();
		else if (popRedFromCenter != null && popRedFromCenter.isShowing())
			popRedFromCenter.dismiss();
		else if (popImg != null && popImg.isShowing())
			popImg.dismiss();
		else
			finish();
	}

	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
}
