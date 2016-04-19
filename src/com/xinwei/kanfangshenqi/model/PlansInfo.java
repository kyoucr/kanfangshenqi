package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;


public class PlansInfo extends BasicSlideItem implements Serializable{
	private String buildingId;
	private String planId;
	private String address;
	private String buildingName;
	private String longitude;
	private String latitude;
	private String averagePrice;
	private String buildingPhone;
	private String position;
	private String status;
	private String createTime;
	private String scancodePrice;
	private String appointmentTime;
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getBuildingPhone() {
		return buildingPhone;
	}
	public void setBuildingPhone(String buildingPhone) {
		this.buildingPhone = buildingPhone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getScancodePrice() {
		return scancodePrice;
	}
	public void setScancodePrice(String scancodePrice) {
		this.scancodePrice = scancodePrice;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
}
