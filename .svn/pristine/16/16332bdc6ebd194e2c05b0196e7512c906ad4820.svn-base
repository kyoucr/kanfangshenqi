package com.xinwei.kanfangshenqi.util;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

public class GetLatLon implements AMapLocationListener {

    //经纬度
	private  double latitude;
	private  double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	public  GetLatLon(Context context ){ //构造传入
		 mlocationClient = new AMapLocationClient(context);
		 mLocationOption = new AMapLocationClientOption();
//		 mlocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
		 mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置定位监听
		 mlocationClient.setLocationListener(this); 
		//设置是否返回地址信息（默认返回地址信息）
		 mLocationOption.setNeedAddress(true);
		 //设置是否只定位一次,默认为false
		 mLocationOption.setOnceLocation(false);
		 //设置是否强制刷新WIFI，默认为强制刷新
		 mLocationOption.setWifiActiveScan(true);
		 //设置是否允许模拟位置,默认为false，不允许模拟位置
		 mLocationOption.setMockEnable(false);
		 //设置定位间隔,单位毫秒,默认为2000ms
		 mLocationOption.setInterval(2000);
		 //给定位客户端对象设置定位参数
		 mlocationClient.setLocationOption(mLocationOption);
		 //启动定位
		 mlocationClient.startLocation();
	}
	public void destroyAMapLocationListener() { //取消经纬度监听
		mlocationClient.onDestroy();
	}
	@Override
	public void onLocationChanged(AMapLocation arg0) {
		// TODO Auto-generated method stub
		latitude = arg0.getLatitude();
		longitude = arg0.getLongitude();
	}
}