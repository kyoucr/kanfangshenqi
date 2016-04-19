package com.xinwei.kanfangshenqi.response;

import java.util.ArrayList;

import com.xinwei.kanfangshenqi.network.KfsqHttpResponse;
import com.xinwei.kanfangshenqi.model.DataList;
public class NameOfHousesResponse extends KfsqHttpResponse{
	private String status;
	private String msg;
	private ArrayList<DataList> dataList;
	private String dataCount;
	private int pageCount;
	private String pageRowCnt;
	private String areaId;
	private String areaLongitude;
	private String areaLatitude;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<DataList> getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList<DataList> dataList) {
		this.dataList = dataList;
	}
	public String getDataCount() {
		return dataCount;
	}
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getPageRowCnt() {
		return pageRowCnt;
	}
	public void setPageRowCnt(String pageRowCnt) {
		this.pageRowCnt = pageRowCnt;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaLongitude() {
		return areaLongitude;
	}
	public void setAreaLongitude(String areaLongitude) {
		this.areaLongitude = areaLongitude;
	}
	public String getAreaLatitude() {
		return areaLatitude;
	}
	public void setAreaLatitude(String areaLatitude) {
		this.areaLatitude = areaLatitude;
	}
	
}