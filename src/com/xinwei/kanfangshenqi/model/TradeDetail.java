package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;

public class TradeDetail implements Serializable{
	private String applicationId;
	private String price;
	private String applicationTime;
	private String cashStatus;
	private String dbstatus;//待处理0 成功2 失败3
	private String cashType;
	public static final String STATUS_FAILED = "3";
//	"applicationId":"O20151208115724210002","price":"-50.00","applicationTime":"2015-12-08 11:57:24","cashStatus":"待处理","cashType":"支付宝提现"
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(String applicationTime) {
		this.applicationTime = applicationTime;
	}
	public String getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(String cashStatus) {
		this.cashStatus = cashStatus;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	
	public String getDbstatus() {
		return dbstatus;
	}
	public void setDbstatus(String dbstatus) {
		this.dbstatus = dbstatus;
	}
	public boolean isFailed(){
		return STATUS_FAILED.equals(dbstatus);
	}
	@Override
	public String toString() {
		return "TradeDetail [applicationId=" + applicationId + ", price="
				+ price + ", applicationTime=" + applicationTime
				+ ", cashStatus=" + cashStatus + ", dbstatus=" + dbstatus
				+ ", cashType=" + cashType + "]";
	}
	
}
