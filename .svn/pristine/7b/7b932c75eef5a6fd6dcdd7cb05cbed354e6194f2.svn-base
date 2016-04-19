package com.xinwei.kanfangshenqi.activity;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.ClearEditText;
import com.xinwei.kanfangshenqi.view.ViewBySoftInput;
import com.xinwei.kanfangshenqi.view.ViewBySoftInput.OnSoftInputStatusListener;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class PreferenceActivity extends BaseActivity implements Response.ErrorListener ,OnClickListener,AMapLocationListener,OnGeocodeSearchListener {
	
	@ViewInject(R.id.company_address)
	private ClearEditText company_address;
	@ViewInject(R.id.home_address)
	private ClearEditText home_address;
	@ViewInject(R.id.lltDesc)
	private LinearLayout lltDesc;
	@ViewInject(R.id.rl_company_name)
	private LinearLayout rl_company_name;
	
	
	@ViewInject(R.id.iv_company_positioning)
	private ImageView iv_company_positioning;
	
	@ViewInject(R.id.iv_home_positioning)
	private ImageView iv_home_positioning;
	
	private PreferenceUtils preferenceUtils;
	private static String address;
	private static double latitude;
	private static double longitude;
	private String companyAddress="";
	private String homeAddress="";
	private double companyLatitude;
	private double companyLongitude;
	private double homeLatitude;
	private double homeLongitude;
	private boolean isCompany = true;
	
	private ProgressDialog progDialog = null;
	private GeocodeSearch geocoderSearch;
	private String addressName;
	@ViewInject(R.id.btnSure)
	private Button btnSure;
	@ViewInject(R.id.viewBySoftInput)
	private ViewBySoftInput viewBySoftInput;
	//声明AMapLocationClient类对象
	private static AMapLocationClient locationClient = null;
	private static AMapLocationClientOption locationOption = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activty_preference);
	}
//	@Override
	public void onChildViewLoaded() {
		isShowLeft(false);
		setTitleColorBg(android.R.color.transparent);
		isShowTitleCutline(false);
		setRightTxt("跳过");
		getRightTxt().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utils.moveTo(activity, MainActivity.class,true,null);
			}
		});
		locationClient = new AMapLocationClient(this.getApplicationContext());
		locationOption = new AMapLocationClientOption();
		// 设置定位模式为低功耗模式
		locationOption.setLocationMode(AMapLocationMode.Battery_Saving);
		// 设置定位监听
		locationClient.setLocationListener(this);
		//设置为单次定位
//		locationOption.setOnceLocation(true);
		iv_company_positioning.setOnClickListener(this);
		iv_home_positioning.setOnClickListener(this);
		preferenceUtils = PreferenceUtils.getInstance(this);
		btnSure.setOnClickListener(this);
		init();
		// 设置是否需要显示地址信息
		locationOption.setNeedAddress(true);
		// 设置定位参数
		locationClient.setLocationOption(locationOption);
		// 启动定位
		locationClient.startLocation();
		
		viewBySoftInput.setOnSoftInputStatusListener(new OnSoftInputStatusListener() {
			
			@Override
			public void onShow() {
				if(lltDesc!=null&&lltDesc.getVisibility()==View.VISIBLE){
					LinearLayout.LayoutParams params = (LayoutParams) rl_company_name.getLayoutParams();
					params.topMargin = DensityUtil.dip2px(37f);
					rl_company_name.setLayoutParams(params);
					lltDesc.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onHidden() {
				if(lltDesc!=null&&lltDesc.getVisibility()==View.GONE){
					LinearLayout.LayoutParams params = (LayoutParams) rl_company_name.getLayoutParams();
					params.topMargin = DensityUtil.dip2px(80f);
					rl_company_name.setLayoutParams(params);
					lltDesc.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	public void onReloadData() {
	}
	@Override
	public void onBackPressed() {
		Utils.moveTo(this, MainActivity.class,true,null);
	}
	@Override
	public String getRequestTag() {
		return PreferenceActivity.class.getSimpleName();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSure:
			if(checkInput()){
				getLatlon(company_address.getText().toString());
				getLatlon(home_address.getText().toString());
				preferenceUtils.setSettingUserCompanyAddr(company_address.getText().toString());
				preferenceUtils.setSettingUserHomeAddr(home_address.getText().toString());
				preferenceUtils.setSettingUserCompanyLongitude(companyLongitude+"");
				preferenceUtils.setSettingUserCompanyLatitude(companyLatitude+"");
				preferenceUtils.setSettingUserHomeLongitude(homeLongitude+"");
				preferenceUtils.setSettingUserHomeLatitude(homeLatitude+"");
//				Utils.moveTo(activity, MainActivity.class);
//				finish();
			}
			break;
		case R.id.tv_company_positioning:
			company_address.setText(address);
			companyAddress = address;
			companyLatitude = latitude;
			companyLongitude = longitude;
			break;
		case R.id.iv_company_positioning:
			company_address.setText(address);
			companyAddress = address;
			companyLatitude = latitude;
			companyLongitude = longitude;
			break;
		case R.id.tv_home_positioning:
			home_address.setText(address);
			homeAddress = address;
			homeLatitude = latitude;
			homeLongitude = longitude;
			break;
		case R.id.iv_home_positioning:
			home_address.setText(address);
			homeAddress = address;
			homeLatitude = latitude;
			homeLongitude = longitude;
			break;	
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			locationClient.onDestroy();
			locationClient = null;
			locationOption = null;
		}
	}
	private boolean checkInput() {
		if (StringUtils.isEmpty(company_address.getText().toString())) {
			Utils.makeToastAndShow(this, "公司地址不能为空,请重新输入", Toast.LENGTH_SHORT);
			return false;
		}
		if (StringUtils.isEmpty(home_address.getText().toString())) {
			Utils.makeToastAndShow(this, "住址不能为空，请重新输入", Toast.LENGTH_SHORT);
			return false;
		}
		return true;
	}
	@Override
	public void onLocationChanged(AMapLocation location) {
		StringBuffer sb = new StringBuffer();
		if(location.getErrorCode() == 0){
//			sb.append("省            : " + location.getProvince() + "\n");
//			sb.append("市            : " + location.getCity() + "\n");
//			sb.append("区            : " + location.getDistrict() + "\n");
			sb.append(location.getAddress());
			location.getLatitude();
			latitude = location.getLatitude(); // 经度
			longitude = location.getLongitude(); // 纬度
		}else {
			//定位失败
//			sb.append("定位为失败" + "\n");
//			sb.append("错误码:" + location.getErrorCode() + "\n");
//			sb.append("错误信息:" + location.getErrorInfo() + "\n");
//			sb.append("错误描述:" + location.getLocationDetail() + "\n");
			ToastUtil.show(PreferenceActivity.this, "定位失败，请检查权限");
		}
		address=sb.toString();
	}
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		geocoderSearch = new GeocodeSearch(this);
		geocoderSearch.setOnGeocodeSearchListener(this);
		progDialog = new ProgressDialog(this);

	}
	/**
	 * 显示进度条对话框
	 */
	public void showDialog() {
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在获取地址");
		progDialog.show();
	}

	/**
	 * 隐藏进度条对话框
	 */
	public void dismissDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}
	/**
	 * 响应地理编码
	 */
	public void getLatlon(final String name) {
		showDialog();
		GeocodeQuery query = new GeocodeQuery(name, "024");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
		geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
	}

	@Override
	public void onGeocodeSearched(GeocodeResult result, int rCode) {
		dismissDialog();
		if (rCode == 0) {
			if (result != null && result.getGeocodeAddressList() != null
					&& result.getGeocodeAddressList().size() > 0) {
				GeocodeAddress address = result.getGeocodeAddressList().get(0);
				String add = address.getLatLonPoint().toString();
				String[] ary = address.getLatLonPoint().toString().split(",");
				latitude = Double.parseDouble(ary[0]); // 经度
				longitude = Double.parseDouble(ary[1]);; // 纬度
				if(address.getFormatAddress().contains(company_address.getText().toString())){
					companyLongitude =longitude;
					companyLatitude = latitude;
					preferenceUtils.setSettingUserCompanyLongitude(companyLongitude+"");
					preferenceUtils.setSettingUserCompanyLatitude(companyLatitude+"");
				}
				if(address.getFormatAddress().contains(home_address.getText().toString())){
					homeLongitude =longitude;
					homeLatitude = latitude;
					preferenceUtils.setSettingUserHomeLongitude(homeLongitude+"");
					preferenceUtils.setSettingUserHomeLatitude(homeLatitude+"");
				}
				addressName = "经纬度值:" + address.getLatLonPoint() + "\n位置描述:"
						+ address.getFormatAddress();
			} else {
				ToastUtil.show(PreferenceActivity.this, getString(R.string.no_result));
			}

		} else if (rCode == 27) {
			ToastUtil.show(PreferenceActivity.this, getString(R.string.error_network));
		} else if (rCode == 32) {
			ToastUtil.show(PreferenceActivity.this, getString(R.string.error_key));
		} else {
			ToastUtil.show(PreferenceActivity.this,
					getString(R.string.error_other) + rCode);
		}
		if(0!=((int)companyLongitude)&&0!=((int)companyLatitude)&&0!=((int)homeLongitude)&&0!=((int)homeLatitude)){
			Utils.moveTo(this, MainActivity.class,true,null);
		}else{
			ToastUtil.show(PreferenceActivity.this, "输入内容无法转换成经纬度，建议定位一下");
		}
	}
	@Override
	public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
	}
	@Override
	public void onErrorResponse(VolleyError arg0) {
		// TODO Auto-generated method stub
		
	}
}
