package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.util.List;

public class TradeRecordList implements Serializable{
	private int dataCount;
	private int pageRowCnt;
	private int pageCount;
	private List<TradeRecord> dataList;
	
	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getPageRowCnt() {
		return pageRowCnt;
	}

	public void setPageRowCnt(int pageRowCnt) {
		this.pageRowCnt = pageRowCnt;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "TradeRecordList [dataCount=" + dataCount + ", pageRowCnt="
				+ pageRowCnt + ", pageCount=" + pageCount + ", dataList="
				+ dataList + "]";
	}

	public List<TradeRecord> getDataList() {
		return dataList;
	}

	public void setDataList(List<TradeRecord> dataList) {
		this.dataList = dataList;
	}


	public class TradeRecord implements Serializable{
		private String applicationId;
		private String serviceTime;
		private String type;
		private String price;
		private String serviceStatus;
		
		public String getApplicationId() {
			return applicationId;
		}
		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
		public String getServiceTime() {
			return serviceTime;
		}
		public void setServiceTime(String serviceTime) {
			this.serviceTime = serviceTime;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getServiceStatus() {
			return serviceStatus;
		}
		public void setServiceStatus(String serviceStatus) {
			this.serviceStatus = serviceStatus;
		}
		@Override
		public String toString() {
			return "PickCash [applicationId=" + applicationId
					+ ", serviceTime=" + serviceTime + ", type=" + type
					+ ", price=" + price + ", serviceStatus=" + serviceStatus
					+ "]";
		}
		
	}
}

//{"status":"1","msg":"查询成功",
//	"dataList":[
//	            {"applicationId":"O20151116205235006","serviceTime":"12日-星期四","type":"支付宝","price":50,"serviceStatus":"处理失败"},
//	            {"applicationId":"O20151116205235005","serviceTime":"12日-星期四","type":"支付宝","price":50,"serviceStatus":"处理失败"},
//	            {"applicationId":"O20151116205235004","serviceTime":"12日-星期四","type":"支付宝","price":200,"serviceStatus":"处理失败"},
//	            {"applicationId":"O20151116205235003","serviceTime":"12日-星期四","type":"支付宝","price":100,"serviceStatus":"提现成功"},
//	            {"applicationId":"O20151116205235002","serviceTime":"12日-星期四","type":"支付宝","price":100,"serviceStatus":"提现成功"},
//	            {"applicationId":"O20151116205235001","serviceTime":"12日-星期四","type":"支付宝","price":50,"serviceStatus":"提现成功"},
//	            {"applicationId":"O20151116205235007","serviceTime":"12日-星期四","type":"支付宝","price":50,"serviceStatus":"提现成功"}],
//	            "dataCount":4,"pageRowCnt":20,"pageCount":1}