package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xinwei.kanfangshenqi.model.BuildingDetail.Building;

public class DataList implements Serializable{

	private String buildingId;
	private String buildingName;
	private String longitude;
	private String latitude;
	private String averagePrice;
	private String section;
	private String position;
	private String scancodePrice;
	private String developer;
	private String banner;
	private String smallBanner;
	private String isPlan;
	private String phone;
	private String hasEnvelope;
	private ArrayList<FeatureList> featureList;
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public ArrayList<FeatureList> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(ArrayList<FeatureList> featureList) {
		this.featureList = featureList;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSmallBanner() {
		return smallBanner;
	}
	public void setSmallBanner(String smallBanner) {
		this.smallBanner = smallBanner;
	}
	
	public String getHasEnvelope() {
		return hasEnvelope;
	}
	public void setHasEnvelope(String hasEnvelope) {
		this.hasEnvelope = hasEnvelope;
	}
	@Override
	public String toString() {
		return "DataList [buildingId=" + buildingId + ", buildingName=" + buildingName + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", averagePrice=" + averagePrice + ", section=" + section + ", position="
				+ position + ", scancodePrice=" + scancodePrice + ", developer=" + developer + ", banner=" + banner
				+ ", smallBanner=" + smallBanner + ", isPlan=" + isPlan + ", phone=" + phone + ", featureList="
				+ featureList + "]";
	}
	
}
