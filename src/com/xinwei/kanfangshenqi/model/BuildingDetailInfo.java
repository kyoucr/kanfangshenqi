package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinwei.kanfangshenqi.model.BuildingDetail.Building;

public class BuildingDetailInfo implements Serializable{

	private String buildingId;
	private String buildingName;
	private String scancodePrice;
	private String developer;
	private String banner;
	private String isPlan;//是否添加计划
	private String address;
	private String buildingInfo;//楼盘详情
	private String surrounding;//周边配套
	private List<HouseType> unitMaps;
	private String buildingPhone;
	private String isFollow;//是否关注
	private String followId;
	private List<Building> buildingComments;
	private String areaId;
	private String priceId;
	private List<DataList> synthesize;//综合查询
	private List<DataList> area;//同地段
	private List<DataList> price;//同价位
	private List<DataList> feature;//同特色
	private List<FeatureList> features;
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getScancodePrice() {
		return scancodePrice;
	}
	public void setScancodePrice(String scancodePrice) {
		this.scancodePrice = scancodePrice;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getIsPlan() {
		return isPlan;
	}
	public void setIsPlan(String isPlan) {
		this.isPlan = isPlan;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<HouseType> getUnitMaps() {
		return unitMaps;
	}
	public void setUnitMaps(List<HouseType> unitMaps) {
		this.unitMaps = unitMaps;
	}
	
	public String getBuildingPhone() {
		return buildingPhone;
	}
	public void setBuildingPhone(String buildingPhone) {
		this.buildingPhone = buildingPhone;
	}
	public String getIsFollow() {
		return isFollow;
	}
	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}
	public List<Building> getBuildingComments() {
		return buildingComments;
	}
	public void setBuildingComments(List<Building> buildingComments) {
		this.buildingComments = buildingComments;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getPriceId() {
		return priceId;
	}
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	public List<DataList> getSynthesize() {
		return synthesize;
	}
	public void setSynthesize(List<DataList> synthesize) {
		this.synthesize = synthesize;
	}
	public List<DataList> getArea() {
		return area;
	}
	public void setArea(List<DataList> area) {
		this.area = area;
	}
	public List<DataList> getPrice() {
		return price;
	}
	public void setPrice(List<DataList> price) {
		this.price = price;
	}
	public List<DataList> getFeature() {
		return feature;
	}
	public void setFeature(List<DataList> feature) {
		this.feature = feature;
	}
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	
	public List<FeatureList> getFeatures() {
		return features;
	}
	public void setFeatures(List<FeatureList> features) {
		this.features = features;
	}
	public List<Desc> getBuildingInfo() {
		 Type type = new TypeToken<List<Desc>>() {  
	        }.getType();
		return new Gson().fromJson(buildingInfo, type);
	}
	public List<Desc> getSurrounding() {

		 Type type = new TypeToken<List<Desc>>() {  
	        }.getType();
		return new Gson().fromJson(surrounding, type);
	}
	@Override
	public String toString() {
		return "BuildingDetailInfo [buildingId=" + buildingId + ", buildingName=" + buildingName + ", scancodePrice="
				+ scancodePrice + ", developer=" + developer + ", banner=" + banner + ", isPlan=" + isPlan
				+ ", address=" + address + ", buildingInfo=" + buildingInfo + ", surrounding=" + surrounding
				+ ", unitMaps=" + unitMaps + ", buildingPhone=" + buildingPhone + ", isFollow=" + isFollow
				+ ", followId=" + followId + ", buildingComments=" + buildingComments + ", areaId=" + areaId
				+ ", priceId=" + priceId + ", synthesize=" + synthesize + ", area=" + area + ", price=" + price
				+ ", feature=" + feature + ", features=" + features + "]";
	}
	
}
